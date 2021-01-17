package com.ipro.sns.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "user")
@NoArgsConstructor //기본 생성자 자동 추가 ex) public UserModel(){}
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, length = 100)
    private String userpw;
    @Column(length = 25)
    private String usernick;
    @Column(nullable = false, length = 50)
    private String userfull;
    @Column
    private String userimg;
    @Column
    private String userintro;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @OneToMany(mappedBy = "userid")
    @OrderBy("id desc")
    private List<PostModel> postModels = new ArrayList<>();

    @Builder
    public UserModel(int id, String username, String userpw, String usernick, String userfull,
                     String userimg, String userintro, Role role) {
        this.id = id;
        this.username = username;
        this.userpw = userpw;
        this.usernick = usernick;
        this.userfull = userfull;
        this.userimg = userimg;
        this.userintro = userintro;
        this.role = role;
    }



}
