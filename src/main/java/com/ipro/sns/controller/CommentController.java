package com.ipro.sns.controller;


import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    //comment insert proc
    @RequestMapping("")
    public int commentInsert(@RequestParam int postid, @RequestParam int userid, @RequestParam String content)
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
