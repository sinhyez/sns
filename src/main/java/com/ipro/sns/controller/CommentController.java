package com.ipro.sns.controller;


import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/comment")
@ResponseBody
public class CommentController {

    private final CommentService commentService;

    //comment insert proc
    @RequestMapping("/insert")
    public int commentInsert(@RequestParam PostModel postid, @RequestParam UserModel userid, @RequestParam String content)
        throws Exception {

        commentService.cSave(userid, postid, content);

        return 1;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<CommnetModel> commentList(Model model, int id) {
        return commentService.findByPostid(id);
    }

}