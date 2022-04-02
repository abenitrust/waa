package com.waa.lab.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    List<Comment> comments = new ArrayList<>();
}
