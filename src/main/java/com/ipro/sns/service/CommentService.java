package com.ipro.sns.service;

import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.CommentDto;
import com.ipro.sns.model.dto.UserDto;
import com.ipro.sns.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<CommnetModel> findByPostid(int postid) {
        return commentRepository.findByPostidId(postid);
    }
    public List<CommnetModel> findByAll() { return commentRepository.findAll(); }
    public int countByPostid(int postid) {
        return commentRepository.countByPostidId(postid);
    }
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
    
    //코멘트 저장 프로세스
    public void cSave(UserModel userid, PostModel postid, String content) {

        CommentDto commentDto = new CommentDto();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Optional<UserModel> user = userService.findById(userid.getId());
        Optional<PostModel> post = postService.findById(postid.getId());

        UserModel userWrapper = user.get();
        PostModel postWrapper = post.get();

        commentDto.setContent(content);
        commentDto.setUserid(userWrapper);
        commentDto.setPostid(postWrapper);
        commentDto.setWrite_date(timestamp);

        commentRepository.save(commentDto.toEntity());
        
    }

}
