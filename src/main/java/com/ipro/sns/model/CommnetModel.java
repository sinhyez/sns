package com.ipro.sns.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class CommnetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserModel userid;

    @ManyToOne
    @JoinColumn(name = "postid")
    private PostModel postid;

    private Timestamp write_date;

    @Builder
    public CommnetModel(int id, String content, UserModel userid, PostModel postid, Timestamp write_date) {
        this.id = id;
        this.content = content;
        this.userid = userid;
        this.postid = postid;
        this.write_date = write_date;
    }
}
