package com.ipro.sns.controller;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.repository.PostRepository;
import com.ipro.sns.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;
    private final PostRepository postRepository;

    @GetMapping("/")
    public String index(){
        return "view/index";
    }

    //main feed
    @GetMapping("/ipro/main")
    public String main(Model model) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //현재 로그인 되어있는 아이디 검색
        model.addAttribute("user", userService.findByUsername(username));
        //유저아이디를 통해 유저테이블에 존재하는 현재 유저의 모든정보 전달
        return "view/main";
    }

    //user Profile page
    @GetMapping("/ipro/main/user/{usernick}")
    public String user_main(@PathVariable("usernick") String usernick, Model model) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> userModel = userService.findByUsername(username);
        UserModel user = userModel.get();
        
        //이미지 카운트
        int imgCount = user.getPostModels().size();
        model.addAttribute("imgCount", imgCount);

        List<PostModel> postModel = postRepository.findByUseridId(user.getId());
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("post", postModel);
        

        return "view/user/user_main";
    }

    //posting page
    @GetMapping("/ipro/post")
    public String post(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));

        return "view/post/posting";
    }

}
