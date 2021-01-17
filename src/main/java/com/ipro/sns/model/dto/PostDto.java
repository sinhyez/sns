package com.ipro.sns.model.dto;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class PostDto {

    private int id;
    private String caption;
    private String imgurl;
    private UserModel userid;
    private Timestamp create_date;
    private Timestamp update_date;

    public PostModel toEntity() {
        return PostModel.builder()
                .id(id)
                .caption(caption)
                .userid(userid)
                .imgurl(imgurl)
                .create_date(create_date)
                .build();
    }
}
