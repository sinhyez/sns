package com.ipro.sns.repository;

import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.LikesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<LikesModel, Integer> {

    LikesModel findByUseridIdAndPosidId(int userid, int postid);

    int countByPosidId(int postid);

    @Transactional
    void deleteByPosidAndUserid(PostModel postid, UserModel userid);

}
