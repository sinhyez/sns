package com.ipro.sns.repository;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostModel, Integer> {

    List<PostModel> findByUserOrderByIdDesc(UserModel userid);

    List<PostModel> findByUserIdOrderByIdDesc(int id);

//    Optional<PostDto> findById(int id);
    Optional<PostModel> findById(int id);

    @Transactional
    void deleteById(int id);

}
