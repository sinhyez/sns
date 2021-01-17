package com.ipro.sns.model.dto;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class PostDto {

    private int id;
    private String caption;
    private String imgurl;
    private UserModel user;
    private Timestamp create_date;
    private Timestamp update_date;

    public PostModel toEntity() {
        return PostModel.builder()
                .id(id)
                .caption(caption)
                .user(user)
                .imgurl(imgurl)
                .create_date(create_date)
                .build();
    }

    @Builder
    public PostDto(int id, UserModel user, String caption, String imgurl,
                   Timestamp create_date, Timestamp update_date) {
        this.id = id;
        this.user = user;
        this.caption = caption;
        this.imgurl = imgurl;
        this.create_date = create_date;
        this.update_date = update_date;
    }
}
