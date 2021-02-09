package com.ipro.sns.model.dto;


import com.ipro.sns.model.FollowModel;
import com.ipro.sns.model.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
public class FollowDto {

    private int id;
    private UserModel followerid;
    private UserModel followingid;
    private Timestamp followdate;

    public FollowModel toEntity() {

        return FollowModel.builder()
                .id(id)
                .followerid(followerid)
                .followingid(followingid)
                .followdate(followdate)
                .build();

    }

    @Builder
    public FollowDto(int id, UserModel followerid, UserModel followingid, Timestamp followdate) {
        this.id = id;
        this.followerid = followerid;
        this.followingid = followingid;
        this.followdate = followdate;
    }

}
