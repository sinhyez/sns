package com.ipro.sns.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "likes")
@NoArgsConstructor
public class LikesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserModel userid;

    @ManyToOne
    @JoinColumn(name = "postid")
    private PostModel posid;

    private Timestamp create_date;

    @Builder
    public LikesModel(int id, UserModel userid, PostModel posid, Timestamp create_date) {
        this.id = id;
        this.userid = userid;
        this.posid = posid;
        this.create_date = create_date;
    }

}
