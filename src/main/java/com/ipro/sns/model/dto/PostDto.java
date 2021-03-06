package com.ipro.sns.model.dto;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Comparator;

@Data
@ToString
@NoArgsConstructor
public class PostDto implements Comparator<PostModel> {

    private int id;
    private String caption;
    private String imgurl;
    private UserModel user;
    private Timestamp create_date;
    private Timestamp update_date;
    private boolean likeState;

    public PostModel toEntity() {
        return PostModel.builder()
                .id(id)
                .caption(caption)
                .user(user)
                .create_date(create_date)
                .likeState(likeState)
                .build();
    }

    @Builder
    public PostDto(int id, UserModel user, String caption, String imgurl,
                   Timestamp create_date, Timestamp update_date, boolean likeState) {
        this.id = id;
        this.user = user;
        this.caption = caption;
        this.imgurl = imgurl;
        this.create_date = create_date;
        this.update_date = update_date;
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
