package com.ipro.sns.controller;


import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.UserDto;
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

    //회원가입 처리 프로세스
    @PostMapping("/ipro/signup")
    public String signUpProc(@Valid UserDto userDto, BindingResult bindingResult, Model model) throws Exception{
        if (userService.check(userDto, bindingResult)) {
            model.addAttribute("userDto", userDto);
            return "view/signup";
        }
        userService.joinUser(userDto);
        return "redirect:/ipro/login";
    }

    //헤더에서 유저프로필로 이동할때 처리되는 프로세스
    @RequestMapping("/ipro/main/user/")
    public String userProfile() throws Exception{

        try {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<UserModel> userModel = userService.findByUsername(username);

            return "redirect:/ipro/main/user/" + userModel.get().getUsernick();

        } catch (Exception e) {

            return "view/login";

        }

    }

    //유저 프로필 업데이트
    @RequestMapping(value = "/ipro/user/edit/{usernick}")
    public String updateUser(@PathVariable("usernick") String usernick, Model model) throws Exception {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));

        return "view/user/edit";
    }

    //유저 프로필 사진 업데이트
    @RequestMapping(value = "/ipro/user/img_edit")
    public String imgEdit(Model model) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));
        return "view/user/img_edit";
    }

    @RequestMapping("/ipro/user/img_insert")
    public String userImgInsert(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));

        return "view/user/img_edit";
    }
    //유저 프로필 사진 업데이트 프로세스
    @PutMapping(value = "/ipro/user/img_insert/{id}")
    public @ResponseBody String imgInsert(@PathVariable int id,
                             @RequestParam("filename") MultipartFile multipartFile,
                                          HttpServletRequest request) throws IOException{

        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        Optional<UserModel> userModel = userService.findById(id);
        String img_path = root_path + "resources\\static\\img\\profile\\" + userModel.get().getUsernick();

        try {
            if (userModel.get().getUserimg() != null) {
                // 기존에 이미지가 있으면 경로를 불러와서 삭제 후 새로운 이미지 삽입
                File file = new File(img_path + userModel.get().getUserimg());
                file.delete();
            }
            multipartFile.transferTo(new File(img_path + multipartFile.getOriginalFilename()));
        } catch (IllegalStateException | IOException exception) {
            exception.printStackTrace();
        }

        userService.userImgEdit(userModel.get().getUsername(),
                userModel.get().getUsernick() + multipartFile.getOriginalFilename());
        return "ok";

    }

    //유저 프로필 업데이트 프로세스
    @RequestMapping(value = "/ipro/user/user_edit")
    public String profileEdit(HttpServletRequest request, Model model) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String redirect = "redirect:/ipro/main/user/";

        //파라메터 값을 넘겨받을 변수지정
        String nick = request.getParameter("usernick");
        String full = request.getParameter("userfull");
        String intro = request.getParameter("userintro");

        //유저 서비스의 업데이트 프로세스 호출
        userService.userProfileEdit(username, nick, full, intro);
        return redirect;
    }

    @RequestMapping("/user/deleteimg/{id}")
    public @ResponseBody String deleteUserimg(@PathVariable int id) {

        Optional<UserModel> userModel = userService.findById(id);
        UserModel userModelWrapper = userModel.get();

        userService.userImgDelete(userModelWrapper.getId());

        return "ok";

    }

    //유저 검색
    @RequestMapping("/search")
    public String search(@RequestParam("search") String search, Model model) throws Exception {

        if (search == "") {
            return "redirect:/";
        }

        model.addAttribute("findUser", userService.findByUsernickContains(search));
        model.addAttribute("countUser", userService.countByUsernickContains(search));
        model.addAttribute("search", search);

        return "view/main/search";

    }

}
