package com.ipro.sns.controller;

import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.PostDto;
import com.ipro.sns.service.CommentService;
import com.ipro.sns.service.PostService;
import com.ipro.sns.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    //comment insert proc
    @RequestMapping("/cInsert")
    @ResponseBody
    public int commentInsert(@RequestParam int postid, @RequestParam int userid, @RequestParam String content)
        throws Exception {

        commentService.cSave(userid, postid, content);

        return 1;
    }

}
