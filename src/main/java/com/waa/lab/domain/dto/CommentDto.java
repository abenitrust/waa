package com.waa.lab.domain.dto;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String name;
    private long postId;
}
