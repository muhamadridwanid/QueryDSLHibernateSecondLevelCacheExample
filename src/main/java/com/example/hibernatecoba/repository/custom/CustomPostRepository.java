package com.example.hibernatecoba.repository.custom;

import com.example.hibernatecoba.dto.response.PostReport2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomPostRepository {
   Page<PostReport2> findReportAllFilter(Pageable pageable, Long id, String title, String description, String content, String authorName);

}
