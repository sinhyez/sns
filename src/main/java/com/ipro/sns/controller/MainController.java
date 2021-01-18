package com.ipro.sns.controller;

import com.ipro.sns.model.FollowModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.PostDto;
import com.ipro.sns.service.FollowService;
import com.ipro.sns.service.PostService;
import com.ipro.sns.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    @GetMapping("/")
    public String index(){
        return "view/index";
    }

    //main feed
    @GetMapping("/ipro/main")
    public String main(Model model) throws Exception {

        //현재 로그인 되어있는 아이디 검색
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> user = userService.findByUsername(username);
        UserModel userWrapper = user.get();

        //login user 가 Follow한 아이디 리스트
        List<FollowModel> followList = followService.findByFollowingid(userWrapper);

        //login user의 post 찾기
        List<PostModel> postList = postService.findByUserIdOrderByIdDesc(userWrapper.getId());

        // following한 유저의 게시글 select 후 user 포스팅과 list 합치기
        for (FollowModel f : followList) {
            List<PostModel> post = postService.findByUserIdOrderByIdDesc(f.getFollowerid().getId());
            for (PostModel p : post) {
                postList.add(p);
            }
        }


        //유저아이디를 통해 유저테이블에 존재하는 현재 유저의 모든정보 전달
        model.addAttribute("user", user);

        model.addAttribute("postlist", postList);
        model.addAttribute("postsize", postList.size());


        return "view/main";
    }

    //user Profile page
    @RequestMapping("/ipro/main/user/{usernick}")
    public String userMain(@PathVariable("usernick") String usernick, Model model) throws Exception {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //유저정보 set
        Optional<UserModel> userModel = userService.findByUsernick(usernick);
        model.addAttribute("user", userModel);

        UserModel user = userModel.get();


        Optional<UserModel> loginUser = userService.findByUsername(username);
        UserModel loginUserWrapper = loginUser.get();
        model.addAttribute("loginId", loginUser.get().getId());


        //이미지 카운트
        int imgCount = user.getPostModels().size();
        model.addAttribute("imgCount", imgCount);

        //포스팅 리스트
        List<PostDto> postDtoList = postService.getUserPostList(user);
        model.addAttribute("postlist", postDtoList);

        //팔로우 카운팅
        int followerCount = followService.followerCounting(user);
        int followingCount = followService.followingCounting(user);
        boolean checkFollow = followService.checkFollow(user.getId(), loginUserWrapper.getId());
        model.addAttribute("followcount", checkFollow);
        model.addAttribute("follower", followerCount);
        model.addAttribute("following", followingCount);

        return "view/user/user_main";
    }

    //posting page
    @GetMapping("/ipro/post")
    public String postPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));

        return "view/post/posting";
    }

}
