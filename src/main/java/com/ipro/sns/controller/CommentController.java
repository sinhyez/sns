package com.ipro.sns.controller;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/comment")
@ResponseBody
public class CommentController {

    private final CommentService commentService;

    //comment insert proc
    @PostMapping("/insert")
    public int commentInsert(@RequestParam PostModel postid, @RequestParam UserModel userid, @RequestParam String content)
        throws Exception {

        commentService.cSave(userid, postid, content);

        return 1;
    }

    @DeleteMapping("/delete/{id}")
    public int deleteComment(@PathVariable int id) throws Exception {

        commentService.deleteById(id);

        return -1;
    }

}
