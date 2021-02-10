package com.ipro.sns.controller;


import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.UserDto;

import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    protected static final String loImgPath = "C:/Users/yoon sung/Desktop/upload/profile/";
    protected static final String ubImgPath = "/home/ubuntu/apps/upload/profile/";


    //회원가입 처리 프로세스
    @PostMapping("/signup")
    public String signUpProc(@Valid UserDto userDto, BindingResult bindingResult, Model model) throws Exception{
        if (userService.check(userDto, bindingResult)) {
            model.addAttribute("userDto", userDto);
            return "view/signup";
        }
        userService.joinUser(userDto);
        return "view/login";
    }

    //유저 프로필 사진 업데이트
    @RequestMapping("/user/img_insert")
    public String userImgInsert(Model model, Principal principal) throws Exception{

        String username = principal.getName();
        model.addAttribute("user", userService.findByUsername(username));

        return "view/user/img_edit";
    }

    //유저 프로필 사진 업데이트 프로세스
    @RequestMapping(value = "/user/img_insert/{id}")
    public ResponseEntity<?> imgInsert(@PathVariable int id,
                             @RequestParam("filename") MultipartFile multipartFile) throws IOException{

        Optional<UserModel> userModel = userService.findById(id);
        String img_path = loImgPath + userModel.get().getUsernick();

        try {
            if (userModel.get().getUserimg() != null) {
                // 기존에 이미지가 있으면 경로를 불러와서 삭제 후 새로운 이미지 삽입
                File file = new File(img_path);
                file.delete();
            }
            multipartFile.transferTo(new File(img_path + multipartFile.getOriginalFilename()));
        } catch (IllegalStateException | IOException exception) {
            exception.printStackTrace();
        }

        userService.userImgEdit(userModel.get().getUsername(),
                userModel.get().getUsernick() + multipartFile.getOriginalFilename());
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    @RequestMapping("/user/deleteimg/{id}")
    public ResponseEntity<?> deleteUserimg(@PathVariable int id) {

        Optional<UserModel> userModel = userService.findById(id);
        UserModel userModelWrapper = userModel.get();

        userService.userImgDelete(userModelWrapper.getId());

        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    //유저 프로필 업데이트
    @RequestMapping(value = "/user/edit/{id}")
    public String updateUser(@PathVariable("id") int id,
                             Model model, Principal principal) throws Exception {

        String username = principal.getName();;
        model.addAttribute("user", userService.findByUsername(username));

        return "view/user/edit";
    }

    //유저 프로필 업데이트 프로세스
    @RequestMapping(value = "/user/user_edit")
    public ResponseEntity<?> userUpadate(Principal principal, UserDto userDto) throws Exception{

        String username = principal.getName();

        userDto.setUsername(username);
        userService.userProfileEdit(principal, userDto);

        return new ResponseEntity<>("ok", HttpStatus.OK);

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

    @RequestMapping("/main/user/")
    public String userProfile(Principal principal) throws Exception{

        try {

            String username = principal.getName();
            Optional<UserModel> userModel = userService.findByUsername(username);

            return "redirect:/main/user/" + userModel.get().getId();

        } catch (Exception e) {

            return "view/login";

        }

    }

}
