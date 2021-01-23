package com.ipro.sns.service;

import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.LikesDto;
import com.ipro.sns.repository.LikesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class LikeService {

    private final LikesRepository likesRepository;

    public int countByPostid(int postid) { return likesRepository.countByPosidId(postid); }

    public LikesModel findByUseridIdAndPosidId(int userid, int postid) {
        return likesRepository.findByUseridIdAndPosidId(userid, postid);
    }

    public void unLike(PostModel postid, UserModel userid) {
        likesRepository.deleteByPosidAndUserid(postid, userid);
    }

    //이미지 좋아요
    public void likeSvae(UserModel userid, PostModel postid) {

        LikesDto likesDto = new LikesDto();

        likesDto.setUserId(userid);
        likesDto.setPostId(postid);

        likesRepository.save(likesDto.toEntity());

    }



}
