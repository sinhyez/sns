package com.ipro.sns.service;

import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.FollowDto;
import com.ipro.sns.repository.FollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

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

        followRepository.save(followDto.toEntity()).getId();

    }

    //연팔로우
    @Transactional
    public void unFollow(UserModel id1, UserModel id2) {
        followRepository.deleteByFollowingidAndFollowerid(id1, id2);
    }

    //팔로우 카운팅
    public boolean countFollow(UserModel id1, UserModel id2) {

        if (followRepository.countByFolloweridAndFollowingid(id1, id2) == 0)
            return false;
        return true;

    }

}
