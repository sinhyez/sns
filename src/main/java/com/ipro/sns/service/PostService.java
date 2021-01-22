package com.ipro.sns.service;

import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.PostDto;
import com.ipro.sns.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void deleteByID(int id) {
        postRepository.deleteById(id);
    }
    public List<PostModel> findByUserIdOrderByIdDesc(int id) {
        return postRepository.findByUserIdOrderByIdDesc(id);
    }
    public Optional<PostModel> findById(int id) { return postRepository.findById(id); }

    //포스트 저장 프로세스
    public void postSave(String caption, UserModel userModel, String imgurl) {

        PostDto postDto = new PostDto();
        postDto.setCaption(caption);
        postDto.setUser(userModel);
        postDto.setImgurl(imgurl);

        postRepository.save(postDto.toEntity());

    }

    //PostDto를 통해 Controller 와 Service 간의 데이터 전달을 위해
    //Repository에서 가져온 PostModel 을 For문을 통해 Dto로 변환
    @Transactional
    public List<PostDto> getUserPostList(UserModel userModel) {

        List<PostModel> postModels = postRepository.findByUserOrderByIdDesc(userModel);
        List<PostDto> postDtoList = new ArrayList<>();

        for (PostModel postModel : postModels) {
            PostDto postDto = PostDto.builder()
                    .id(postModel.getId())
                    .user(userModel)
                    .caption(postModel.getCaption())
                    .imgurl(postModel.getImgurl())
                    .create_date(postModel.getCreate_date())
                    .update_date(postModel.getUpdate_date())
                    .build();

            postDtoList.add(postDto);
        }

        return postDtoList;

    }


}
