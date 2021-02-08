package com.ipro.sns.service;

import com.ipro.sns.model.PostImgModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.PostDto;
import com.ipro.sns.repository.PostImgRepository;
import com.ipro.sns.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;

    public void flush() {
        postRepository.flush();
    }
    public void deleteByID(int id) {
        postRepository.deleteById(id);
    }
    public List<PostImgModel> findByPostid() {
        return postImgRepository.findAll();
    }
    public List<PostModel> findByUserIdOrderByIdDesc(int id) {
        return postRepository.findByUserIdOrderByIdDesc(id);
    }
    public Optional<PostModel> findById(int id) { return postRepository.findById(id); }

    //포스트 저장 프로세스
    public int postSave(PostModel postModel) {

        PostModel p = new PostModel();
        p.setCaption(postModel.getCaption());
        p.setUser(postModel.getUser());

        postRepository.save(p);

        return p.getId();
    }

    public void postImgSave(PostImgModel postImgModel) {

        PostImgModel p = new PostImgModel();

        p.setFilename(postImgModel.getFilename());
        p.setFileoname(postImgModel.getFileoname());
        p.setPostid(postImgModel.getPostid());

        postImgRepository.save(p);
    }

    public String rand(String oriName, byte[] fileData, String path) throws Exception{
        UUID uuid = UUID.randomUUID();
        String saveName = uuid.toString() + "_" + oriName;
        File target = new File(path, saveName);

        FileCopyUtils.copy(fileData, target);

        return saveName;
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
                    .create_date(postModel.getCreate_date())
                    .update_date(postModel.getUpdate_date())
                    .build();

            postDtoList.add(postDto);
        }

        return postDtoList;

    }


}
