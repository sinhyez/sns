package com.ipro.sns.repository;

import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommnetModel, Integer> {

    List<CommnetModel> findByPostidId(int id);

    int countByPostidId(int id);

    Optional<CommnetModel> findByPostid(PostModel postid);

    @Transactional
    void deleteById(int id);

}
