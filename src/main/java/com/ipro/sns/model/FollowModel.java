package com.ipro.sns.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "followerid")
    private UserModel followerid; // 당한사람

    @ManyToOne
    @JoinColumn(name = "followingid")
    private UserModel followingid; //한사람

    private Timestamp followdate;

    @Builder
    public FollowModel(int id, UserModel followerid, UserModel followingid, Timestamp followdate) {
        this.id = id;
        this.followerid = followerid;
        this.followingid = followingid;
        this.followdate = followdate;
    }
}
