package com.ipro.sns.repository;

import com.ipro.sns.model.FollowModel;
import com.ipro.sns.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowModel, Integer> {

    //팔로우 유무
    int countByFolloweridIdAndFollowingidId(int followerid, int followingid);

    int countByFollowerid(UserModel followerid);

    int countByFollowingid(UserModel followingid);

    List<FollowModel> findByFollowerid( UserModel followerid);
    List<FollowModel> findByFollowingid(UserModel followingid);

    //언팔로우
    @Transactional
    void deleteByFolloweridIdAndFollowingidId(int followingid, int followerid);

}
