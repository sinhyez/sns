package com.ipro.sns.controller;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    //comment insert proc
    @PostMapping("/insert")
    public ResponseEntity<?> commentInsert(@RequestParam PostModel postid, @RequestParam UserModel userid,
                                        @RequestParam String content) throws Exception {

        commentService.cSave(userid, postid, content);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) throws Exception {

        commentService.deleteById(id);

        return new ResponseEntity<>(-1, HttpStatus.OK);
    }

}
