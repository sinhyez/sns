package com.ipro.sns.repository;

import com.ipro.sns.model.FollowModel;
import com.ipro.sns.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FollowRepository extends JpaRepository<FollowModel, Integer> {

    //팔로우 유무
    int countByFolloweridAndFollowingid(UserModel followerid, UserModel followingid);

    int countByFollowerid(UserModel followerid);

    int countByFollowingid(UserModel followingid);

    //언팔로우
    @Transactional
    void deleteByFollowingidAndFollowerid(UserModel followingid, UserModel followerid);

}
