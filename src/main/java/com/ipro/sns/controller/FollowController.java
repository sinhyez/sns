package com.ipro.sns.controller;

import com.ipro.sns.model.FollowModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.FollowService;
import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    //팔로잉
    @RequestMapping("/follow/{id}")
    public String follow(@PathVariable int id) throws Exception{

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserModel> toUserModel = userService.findById(id);
        UserModel userModel = toUserModel.get();

        followService.save(username, userModel.getUsername());

        return "redirect:/main/user/" + userModel.getUsernick();

    }

    //언팔로우
    @RequestMapping("/unfollow/{id}")
    public String unFollow(@PathVariable int id) throws Exception {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<UserModel> loginUser = userService.findByUsername(username);
        UserModel loginUserWrapper = loginUser.get();

        Optional<UserModel> toUsermodel = userService.findById(id);
        UserModel userModel = toUsermodel.get();

        followService.unFollow(userModel.getId(), loginUserWrapper.getId());

        return "redirect:/main/user/" + userModel.getUsernick();
    }

    @RequestMapping("/followerlist/{id}")
    public String followerlist(@PathVariable int id, Model model) throws Exception {

        Optional<UserModel> user = userService.findById(id);

        List<FollowModel> followerList = followService.findByFollowerid(user.get());
        model.addAttribute("followerlist", followerList);

        return "view/modal/followerlist";
    }

    @RequestMapping("/followinglist/{id}")
    public String followinglist(@PathVariable int id, Model model) throws Exception {

        Optional<UserModel> user = userService.findById(id);

        List<FollowModel> followingList = followService.findByFollowingid(user.get());
        model.addAttribute("followinglist", followingList);

        return "view/modal/followinglist";
    }
}
