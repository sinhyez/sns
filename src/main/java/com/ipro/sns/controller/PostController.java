package com.ipro.sns.controller;

import com.ipro.sns.model.*;
import com.ipro.sns.model.dto.UserDto;
import com.ipro.sns.model.modelutils.LikesCount;
import com.ipro.sns.service.CommentService;
import com.ipro.sns.service.LikeService;
import com.ipro.sns.service.PostService;
import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.io.File;
import java.security.Principal;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

//    protected String loImgPath = "/Users/yoon sung/Desktop/upload/";
    protected String ubImgPath = "/home/ubuntu/apps/upload/";


    //포스팅 업로드 프로세스
    @RequestMapping("/upload")
    public @ResponseBody
    String uploadProc(MultipartHttpServletRequest request,
                    @RequestParam("caption") String caption, @RequestParam("usernick") int userid) throws Exception {

        String path = ubImgPath;
        
        PostModel postModel = new PostModel();

        Optional<UserModel> userModel = userService.findById(userid);
        postModel.setUser(userModel.get());
        postModel.setCaption(caption);

        postModel.setId(postService.postSave(postModel));
        postService.flush();

        List<MultipartFile> multipartFiles = request.getFiles("files");
        for (MultipartFile f : multipartFiles) {
            PostImgModel postImgModel = new PostImgModel();

            String oriName = f.getOriginalFilename();
            String fileName = postService.rand(oriName, f.getBytes(), path);

            postImgModel.setFileoname(oriName);
            postImgModel.setFilename(fileName);
            postImgModel.setPostid(postModel.getId());

            postService.postImgSave(postImgModel);

        }

        return "ok";
    }

    //포스팅 디테일 화면
    @RequestMapping("/post/details/{id}")
    public String postDetails(@PathVariable("id") int id, Model model, Principal principal) throws Exception {

        try {
            //유저정보 set
            String username = principal.getName();
            Optional<UserModel> user = userService.findByUsername(username);

            //포스트 셋
            Optional<PostModel> postModel = postService.findById(id);


            List<CommnetModel> commentlist = commentService.findByPostid(id);
            List<PostImgModel> postImgModelList = postService.findByPostid();
            List<PostModel> postList = postService.findByUserIdOrderByIdDesc(postModel.get().getUser().getId());
            List<LikesCount> likeCount = new ArrayList<>();

            for (PostModel p : postList) {
                LikesCount lc = new LikesCount();
                LikesModel likesModel = likeService.findByUseridIdAndPosidId(user.get().getId(), p.getId());
                if (likesModel != null) {
                    p.setLikeState(true);
                }
                lc.setPostid(p.getId());
                lc.setCount(likeService.countByPostid(p.getId()));
                lc.setUserid(p.getUser().getUsernick());

                likeCount.add(lc);
            }

            model.addAttribute("img", postImgModelList);
            model.addAttribute("likes", likeCount);
            model.addAttribute("post", postModel);
            model.addAttribute("loginUser", user);
            model.addAttribute("comment", commentlist);

            return "view/post/post_details";

        } catch (Exception e) {
            return "1";
        }

    }

    //포스팅 삭제
    @Transactional
    @RequestMapping("/deletePost/{id}")
    public String deletePost(@PathVariable int id) {
        String redirect = "redirect:/main/user/";

        Optional<PostModel> postModel = postService.findById(id);
        postService.deleteByID(postModel.get().getId());

        return redirect;
    }

}
