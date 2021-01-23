package com.ipro.sns.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;



@Data
@Entity
@Table(name = "post")
@NoArgsConstructor
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
    private UserModel user;

    private Timestamp create_date;

    private Timestamp update_date;

    @Transient
    private boolean likeState;

    @Transient
    private int likeCount;

    @Builder
    public PostModel(int id, String caption, String imgurl, UserModel user,
                     Timestamp create_date, boolean likeState) {
        this.id = id;
        this.caption = caption;
        this.imgurl = imgurl;
        this.user = user;
        this.create_date = create_date;
        this.likeState = likeState;
    }

}
