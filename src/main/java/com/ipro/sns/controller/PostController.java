package com.ipro.sns.controller;

import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.modelutils.LikesCount;
import com.ipro.sns.service.CommentService;
import com.ipro.sns.service.LikeService;
import com.ipro.sns.service.PostService;
import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;


    //포스팅 업로드 프로세스
    @RequestMapping("/upload")
    public @ResponseBody String uploadProc(@RequestParam("file")MultipartFile file, @RequestParam("caption") String caption,
                      @RequestParam("usernick") String usernick, HttpServletRequest request) throws IOException {

        Optional<UserModel> userModel = userService.findByUsernick(usernick);
        UserModel userModelWrapper = userModel.get();
        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        String path = root_path + "static\\img\\";

        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(path + filename);
        Files.write(filepath, file.getBytes());

        postService.postSave(caption, userModelWrapper, filename);

        return "ok";

    }

    @PutMapping("/post/edit/{id}")
    public String postEdit(@PathVariable int id, @RequestParam("file") MultipartFile file,
                           @RequestParam("caption") String caption, @RequestParam("usernick") String usernick,
                           HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        String path = root_path + "static\\img\\";
        Optional<UserModel> userModel = userService.findByUsernick(usernick);
        UserModel userModelWrapper = userModel.get();

        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(path + filename);
        Files.write(filepath, file.getBytes());

        postService.postEdit(id, caption, userModelWrapper, filename);

        return "ok";
    }

    //포스팅 디테일 화면
    @RequestMapping("/ipro/post/details/{id}")
    public String postDetails(@PathVariable("id") int id, Model model) throws Exception {

        //유저정보 set
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> user = userService.findByUsername(username);

        //포스트 셋
        Optional<PostModel> postModel = postService.findById(id);


        List<CommnetModel> commentlist = commentService.findByPostid(id);
        List<PostModel> postList = postService.findByUserIdOrderByIdDesc(user.get().getId());
        List<LikesCount> likeCount = new ArrayList<>();

        for (PostModel p : postList) {
            LikesCount lc = new LikesCount();
            LikesModel likesModel = likeService.findByUseridIdAndPosidId(user.get().getId(), p.getId());
            if (likesModel != null) {
                p.setLikeState(true);
            }
            lc.setPostid(p.getId());
            lc.setCount(likeService.countByPostid(p.getId()));

            likeCount.add(lc);
        }

        model.addAttribute("likes", likeCount);
        model.addAttribute("post", postModel);
        model.addAttribute("loginUser", user);
        model.addAttribute("comment", commentlist);

        return "view/post/post_details";
    }

    //포스팅 삭제
    @Transactional
    @RequestMapping("/deletePost/{id}")
    public String deletePost(@PathVariable int id) {
        String redirect = "redirect:/ipro/main/user/";

        Optional<PostModel> postModel = postService.findById(id);
        postService.deleteByID(postModel.get().getId());

        return redirect;
    }


}
