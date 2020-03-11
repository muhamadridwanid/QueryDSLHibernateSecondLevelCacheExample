package com.example.hibernatecoba.dto.response;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class PostReport2 {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Date date;
    private String authorName;

    public static List<String> allFields = Arrays.asList(
            "id",
            "title",
            "description",
            "content",
            "date",
            "authorName"
    );

}
