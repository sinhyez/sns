package com.ipro.sns.controller;

import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("/likes")
@AllArgsConstructor
public class LikesController {

    private final LikeService likeService;

    //이미지 좋아요 로직
    @RequestMapping("/like/{postid}")
    public int like(@PathVariable PostModel postid, @RequestParam UserModel userid) {

        likeService.likeSvae(userid, postid);
        return 1;

    }

    //이미지 좋아요 취소
    @DeleteMapping("/unlike/{postid}")
    public int unLike(@PathVariable PostModel postid, @RequestParam UserModel userid) {

        likeService.unLike(postid, userid);

        return -1;

    }

}
