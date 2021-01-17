package com.ipro.sns.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String caption;
    @Column(nullable = false, length = 500)
    private String imgurl;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserModel userid;

    private Timestamp create_date;

    private Timestamp update_date;

    @Builder
    public PostModel(int id, String caption, String imgurl, UserModel userid,
                     Timestamp create_date) {
        this.id = id;
        this.caption = caption;
        this.imgurl = imgurl;
        this.userid = userid;
        this.create_date = create_date;
    }

}
