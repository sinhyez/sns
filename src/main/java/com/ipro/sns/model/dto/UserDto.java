package com.ipro.sns.model.dto;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.modelutils.Role;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class UserDto {

    private int id;
    private String username;
    private String userpw;
    private String usernick;
    private String userfull;
    private String userimg;
    private String userintro;
    private Role role;

    private List<PostModel> postModels;

    public UserModel toEntity(){
        return UserModel.builder()
                .id(id)
                .username(username)
                .userpw(userpw)
                .usernick(usernick)
                .userfull(userfull)
                .userimg(userimg)
                .userintro(userintro)
                .role(role.USER)
                .postModels(postModels)
                .build();
    }

    @Builder
    public UserDto(int id, String username, String userpw, String usernick,
                   String userfull, String userimg, String userintro, Role role, List<PostModel> postModels){
        this.id = id;
        this.username = username;
        this.userpw = userpw;
        this.usernick = usernick;
        this.userfull = userfull;
        this.userimg = userimg;
        this.userintro = userintro;
        this.role = role;
        this.postModels = postModels;
    }

}
