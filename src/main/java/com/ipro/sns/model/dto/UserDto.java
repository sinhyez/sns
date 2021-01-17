package com.ipro.sns.model.dto;

import com.ipro.sns.model.PostModel;
import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotBlank(message = "이메일을 입력해 주세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String userpw;
    @NotBlank(message = "닉네임을 입려해 주세요")
    private String usernick;
    @NotBlank(message = "이름을 입력해 주세요")
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
