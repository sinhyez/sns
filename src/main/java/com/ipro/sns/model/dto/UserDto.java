package com.ipro.sns.model.dto;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.modelutils.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotBlank(message = "Please enter Email")
    private String username;
    @NotBlank(message = "Please enter Password")
    private String userpw;
    @NotBlank(message = "Please enter Username")
    private String usernick;
    @NotBlank(message = "Please enter Full Name")
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
