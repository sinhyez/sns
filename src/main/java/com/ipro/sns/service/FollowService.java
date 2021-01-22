package com.ipro.sns.service;

import com.ipro.sns.model.FollowModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.FollowDto;
import com.ipro.sns.repository.FollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;


    public int followerCounting(UserModel userModel) {
        return followRepository.countByFollowerid(userModel);
    }

    public int followingCounting(UserModel userModel) {
        return followRepository.countByFollowingid(userModel);
    }

    public List<FollowModel> findByFollowerid(UserModel followerid) {
        return followRepository.findByFollowerid(followerid);
    }

    public List<FollowModel> findByFollowingid(UserModel userModel) {
        return followRepository.findByFollowingid(userModel);
    }

    //팔로우 저장
    public void save(String username, String pagename) {

        FollowDto followDto = new FollowDto();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Optional<UserModel> userModelUser = userService.findByUsername(username);
        Optional<UserModel> userModelPage = userService.findByUsername(pagename);

        UserModel userModelUserWrapper = userModelUser.get();
        UserModel userModelPageWrapper = userModelPage.get();


        followDto.setFollowerid(userModelPageWrapper);
        followDto.setFollowingid(userModelUserWrapper);
        followDto.setFollowdate(timestamp);

        followRepository.save(followDto.toEntity());

    }

    //연팔로우
    @Transactional
    public void unFollow(int id1, int id2) {
        followRepository.deleteByFolloweridIdAndFollowingidId(id1, id2);
    }

    //팔로우 카운팅
    public boolean checkFollow(int id1, int id2) {

        if (followRepository.countByFolloweridIdAndFollowingidId(id1, id2) == 0){
            return false;
        } else {
            return true;
        }

    }

}
