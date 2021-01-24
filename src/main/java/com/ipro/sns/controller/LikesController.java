package com.ipro.sns.controller;

import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.LikeService;
import com.ipro.sns.service.PostService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/likes")
@AllArgsConstructor
public class LikesController {

    private final LikeService likeService;
    private final PostService postService;

    //이미지 좋아요 로직
    @RequestMapping("/like/{postid}")
    public @ResponseBody int like(@PathVariable PostModel postid, @RequestParam UserModel userid) throws Exception{

        likeService.likeSvae(userid, postid);
        return 1;

    }

    //이미지 좋아요 취소
    @DeleteMapping("/unlike/{postid}")
    public @ResponseBody int unLike(@PathVariable PostModel postid, @RequestParam UserModel userid) {

        likeService.unLike(postid, userid);

        return -1;

    }

    @RequestMapping("/likelist/{id}")
    public String likemodal(@PathVariable int id, Model model) throws Exception{

        Optional<PostModel> postModel = postService.findById(id);
        List<LikesModel> likesModel = likeService.findByPostid(postModel.get());
        model.addAttribute("like", likesModel);

        return "view/modal/likemodal";
    }
}
