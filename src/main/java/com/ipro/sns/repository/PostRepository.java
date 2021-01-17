package com.ipro.sns.repository;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostModel, Integer> {

    List<PostModel> findByUseridId(int userid);
    List<PostModel> findByUseridOrderByIdDesc(int userid);
    Optional findById(int id);
    //Optional<PostModel> findByUserid(UserModel useid);
}
