package com.ipro.sns.repository;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostModel, Integer> {


    List<PostModel> findByUser_UsernickOrderByIdDesc(UserModel userModel);
    List<PostModel> findByUserOrderByIdDesc(UserModel userid);
    Optional<PostDto> findById(int id);


}
