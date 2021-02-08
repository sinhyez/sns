package com.ipro.sns.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "postimg")
@NoArgsConstructor
public class PostImgModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    int postid;

    @Column
    String filename;

    @Column
    String fileoname;

    @Builder
    public PostImgModel(int id, int postid, String fileoname, String filename) {
        this.id = id;
        this.postid = postid;
        this.fileoname = fileoname;
        this.filename = filename;
    }

}
