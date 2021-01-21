package com.ipro.sns.model.dto;

import com.ipro.sns.model.LikesModel;
import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class LikesDto {

    private int id;
    private UserModel userId;
    private PostModel postId;
    private Timestamp create_date;

    public LikesModel toEntity() {
        return LikesModel.builder()
                .id(id)
                .userid(userId)
                .posid(postId)
                .create_date(create_date)
                .build();
    }

    @Builder
    public LikesDto(int id, UserModel userId, PostModel postId, Timestamp create_date) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.create_date = create_date;
    }

}
