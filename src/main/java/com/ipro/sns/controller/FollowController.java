package com.ipro.sns.controller;

import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.FollowService;
import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @RequestMapping("/follow/{id}")
    public String follow(@PathVariable int id, UserModel userModel) throws Exception{

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> toUserModel = userService.findById(id);
        userModel = toUserModel.get();

        followService.save(username, userModel.getUsername());

        String redirect = "redirect:/ipro/main/user/" + userModel.getUsernick();

        return redirect;

    }

    @RequestMapping("/unfollow/{id}")
    public String unFollow(@PathVariable int id) throws Exception {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<UserModel> loginUser = userService.findByUsername(username);
        UserModel loginUserWrapper = loginUser.get();

        Optional<UserModel> toUsermodel = userService.findById(id);
        UserModel userModel = toUsermodel.get();

        followService.unFollow(loginUserWrapper.getId(), userModel.getId());

        String redirect = "redirect:/ipro/main/user/" + userModel.getUsernick();

        return redirect;
    }
}
