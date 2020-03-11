package com.example.hibernatecoba.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PostCreateRequest {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private Long authorId;

}
