package com.ipro.sns.controller;

import com.ipro.sns.model.*;
import com.ipro.sns.model.dto.PostDto;
import com.ipro.sns.model.dto.UserDto;
import com.ipro.sns.model.modelutils.Count;
import com.ipro.sns.model.modelutils.LikesCount;
import com.ipro.sns.repository.PostRepository;
import com.ipro.sns.service.*;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.List;


@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;
    private final CommentService commentService;
    private final LikeService likeService;

    //main feed
    @RequestMapping("/")
    public String main(Model model) throws Exception {

        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<UserModel> user = userService.findByUsername(username);
            UserModel userWrapper = user.get();

            //유저아이디를 통해 유저테이블에 존재하는 현재 유저의 모든정보 전달
            model.addAttribute("user", user);

            //login user 가 Follow한 아이디 리스트
            List<FollowModel> followList = followService.findByFollowingid(userWrapper);

            //login user의 post 찾기
            List<PostModel> postList = postService.findByUserIdOrderByIdDesc(userWrapper.getId());

            // following한 유저의 게시글 select 후 user 포스팅과 list 합치기
            for (FollowModel f : followList) {
                List<PostModel> post = postService.findByUserIdOrderByIdDesc(f.getFollowerid().getId());
                postList.addAll(post);
            }

            //comment counting
            List<Count> count = new ArrayList<>();
            for (PostModel p : postList) {
                Count c = new Count();
                c.setPostid(p.getId());
                c.setCount(commentService.countByPostid(p.getId()));

                count.add(c);
            }
            model.addAttribute("count", count);

            //like count
            List<LikesCount> likeCount = new ArrayList<>();
            for (PostModel p : postList) {
                LikesCount lc = new LikesCount();
                LikesModel likesModel = likeService.findByUseridIdAndPosidId(userWrapper.getId(), p.getId());
                if (likesModel != null) {
                    p.setLikeState(true);
                }
                lc.setPostid(p.getId());
                lc.setCount(likeService.countByPostid(p.getId()));
                lc.setUserid(p.getUser().getUsernick());

                likeCount.add(lc);
            }
            model.addAttribute("likes", likeCount);

            try {
                List<CommnetModel> commentlist = commentService.findByAll();
                model.addAttribute("c", commentlist);
            } catch (Exception e) {
                e.getStackTrace();
            }

            List<PostImgModel> postImgModelList = postService.findByPostid();
            model.addAttribute("img", postImgModelList);
            model.addAttribute("postlist", postList);

            return "view/main";

        } catch (Exception e) {
            e.getStackTrace();
            return "view/login";
        }

    }

    //user Profile page
    @RequestMapping("/main/user/{usernick}")
    public String userMain(@PathVariable("usernick") String usernick, Model model,
                           Principal principal) throws Exception {

        //유저정보 set
        Optional<UserModel> userModel = userService.findByUsernick(usernick);
        UserModel user = userModel.get();
        model.addAttribute("user", userModel);

        try {
            String username = principal.getName();
            Optional<UserModel> loginUser = userService.findByUsername(username);
            UserModel loginUserWrapper = loginUser.get();
            model.addAttribute("loginId", loginUser.get().getId());

            boolean checkFollow = followService.checkFollow(user.getId(), loginUserWrapper.getId());
            model.addAttribute("followcount", checkFollow);
        } catch (Exception e) {
            e.getStackTrace();
        }

        //이미지 카운트
        int imgCount = user.getPostModels().size();
        model.addAttribute("imgCount", imgCount);

        //포스팅 리스트
        List<PostDto> postDtoList = postService.getUserPostList(user);
        model.addAttribute("postlist", postDtoList);

        //코멘트 카운팅
        List<Count> count = new ArrayList<>();
        for (PostDto p : postDtoList) {
            Count c = new Count();
            c.setPostid(p.getId());
            c.setCount(commentService.countByPostid(p.getId()));
            count.add(c);
        }
        model.addAttribute("count", count);

        //팔로우 카운팅
        int followerCount = followService.followerCounting(user);
        int followingCount = followService.followingCounting(user);
        model.addAttribute("follower", followerCount);
        model.addAttribute("following", followingCount);

        //like counting
        List<LikesCount> likeCount = new ArrayList<>();
        for (PostDto p : postDtoList) {
            LikesCount lc = new LikesCount();
            lc.setPostid(p.getId());
            lc.setCount(likeService.countByPostid(p.getId()));
            likeCount.add(lc);
        }

        model.addAttribute("likes", likeCount);

        List<PostImgModel> postImgModelList = postService.findByPostid();
        model.addAttribute("img", postImgModelList);

        return "view/user/user_main";

    }

    //로그인 처리
    @RequestMapping("/login")
    public String login(HttpSession session, String username){
        //로그인을 처리하고 세션에 유저정보를 저장(세선 최대 시간 60분)
        session.setAttribute("user", userService.findByUsername(username));
        session.setMaxInactiveInterval(60 * 60);
        return "view/login";
    }

    //회원가입 페이지
    @GetMapping("/signup")
    public String signup(UserDto userDto){
        return "view/signup";
    }

    //posting page
    @GetMapping("/post")
    public String postPage(Model model) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));

        return "view/post/posting";
    }

}
