package com.ipro.sns.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Comparator;


@Data
@Entity
@Table(name = "post")
@NoArgsConstructor
public class PostModel implements Comparator<PostModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String caption;

//    @Column(nullable = false, length = 500)
//    private String imgurl;

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
    public PostModel(int id, String caption, UserModel user,
                     Timestamp create_date, boolean likeState) {
        this.id = id;
        this.caption = caption;
        this.user = user;
        this.create_date = create_date;
        this.likeState = likeState;
    }

    @Override
    public int compare(PostModel p1, PostModel p2) {

        long l1 = p1.getCreate_date().getTime();
        long l2 = p2.getCreate_date().getTime();

        if (l1 > l2) {
            return -1;
        }
        else {
            return 1;
        }

    }

}
