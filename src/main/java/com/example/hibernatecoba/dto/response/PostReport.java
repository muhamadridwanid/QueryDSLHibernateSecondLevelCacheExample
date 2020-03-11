package com.example.hibernatecoba.dto.response;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface PostReport {

    public static List<String> allFields = Arrays.asList(
            "id",
            "title",
            "description",
            "content",
            "date",
            "authorName"
    );

    Long getId();

    String getTitle();

    String getDescription();

    String getContent();

    Date getDate();

    String getAuthorName();
}
