package com.ipro.sns.model.modelutils;

import lombok.Data;

@Data
public class LikesCount {

    private int postid;
    private int count;
    private boolean likeState;
    private int userid;

}
