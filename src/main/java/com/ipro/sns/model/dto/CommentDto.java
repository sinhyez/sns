package com.ipro.sns.model.dto;

import com.ipro.sns.model.CommnetModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CommentDto {

    private int id;
    private UserModel userid;
    private PostModel postid;
    private String content;
    private Timestamp write_date;

    public CommnetModel toEntity() {
        return CommnetModel.builder()
                .id(id)
                .userid(userid)
                .postid(postid)
                .content(content)
                .write_date(write_date)
                .build();
    }

    @Builder
    public CommentDto(int id, UserModel userid, PostModel postid, String content, Timestamp write_date) {
        this.id = id;
        this.content = content;
        this.userid = userid;
        this.postid = postid;
        this.write_date = write_date;
    }

}
