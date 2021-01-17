package com.ipro.sns.controller;


import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.UserDto;
import com.ipro.sns.repository.UserRepository;
import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //로그인 처리
    @RequestMapping("/ipro/login")
    public String login(HttpSession session, String username){
        //로그인을 처리하고 세션에 유저정보를 저장(세선 최대 시간 60분)
        session.setAttribute("user", userService.findByUsername(username));
        session.setMaxInactiveInterval(60 * 60);
        return "view/login";
    }

    //회원가입 페이지
    @GetMapping("/ipro/signup")
    public String signup(UserDto userDto){
        return "view/signup";
    }

    //회원가입 처리 프로세스
    @PostMapping("/ipro/signup")
    public String Signupproc(@Valid UserDto userDto, BindingResult bindingResult, Model model) throws Exception{
        if (userService.check(userDto, bindingResult)) {
            model.addAttribute("userDto", userDto);
            return "view/signup";
        }
        userService.joinUser(userDto);
        return "redirect:/ipro/login";
    }

    //유저 프로필 업데이트
    @RequestMapping(value = "/ipro/user/edit/{usernick}")
    public String update_user(@PathVariable("usernick") String usernick, Model model) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));
        return "view/user/edit";
    }

    //유저 프로필 사진 업데이트
    @RequestMapping(value = "/ipro/user/img_edit")
    public String img_edit(Model model) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));
        return "view/user/img_edit";
    }

    //유저 프로필 사진 업데이트 프로세스
    @RequestMapping(value = "/ipro/user/img_insert")
    public String img_insert(HttpServletRequest request,
                             @RequestParam("filename") MultipartFile multipartFile, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> model1 = userService.findByUsername(username);
        String img_path = "C:/Users/yoon sung/Desktop/java/sns/src/main/resources/static/img/profile/" + model1.get().getUsernick();
        String redirect = "redirect:/ipro/main/user/" + model1.get().getUsernick();
        String msg = null;

        try {
            if (model1.get().getUserimg() != null) {
                // 기존에 이미지가 있으면 경로를 불러와서 삭제 후 새로운 이미지 삽입
                File file = new File(img_path + model1.get().getUserimg());
                file.delete();
            }
            multipartFile.transferTo(new File(img_path + multipartFile.getOriginalFilename()));
        } catch (IllegalStateException | IOException exception) {
            exception.printStackTrace();
        }

        userService.img_edit(username,
                model1.get().getUsernick() + multipartFile.getOriginalFilename());
        return redirect;

    }

    //유저 프로필 업데이트 프로세스
    @RequestMapping(value = "/ipro/user/user_edit")
    public String profile_edit(HttpServletRequest request, Model model) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> model1 = userService.findByUsername(username);
        String redirect = "redirect:/ipro/main/user/" + model1.get().getId();

        //파라메터 값을 넘겨받을 변수지정
        String nick = request.getParameter("usernick");
        String full = request.getParameter("userfull");
        String intro = request.getParameter("userintro");

        //유저 서비스의 업데이트 프로세스 호출
        userService.user_edit(username, nick, full, intro);
        return redirect;
    }

    @RequestMapping("/ipro/user/img_delete")
    public String deleteImg(HttpServletRequest request) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> model = userService.findByUsername(username);
        String redirect = "redirect:/ipro/main/user/" + model.get().getUsernick();

        userService.deleteUserimg(username);


        return redirect;
    }

}
