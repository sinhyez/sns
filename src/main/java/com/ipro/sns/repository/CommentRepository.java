package com.ipro.sns.repository;

import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommnetModel, Integer> {

    List<CommentDto> findByPostid(PostModel postid);

    @Transactional
    void deleteById(int id);

}
