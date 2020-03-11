package com.example.hibernatecoba.service;

import com.example.hibernatecoba.dto.request.AuthorCreateRequest;
import com.example.hibernatecoba.dto.response.AuthorReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    void storeAuthor(AuthorCreateRequest createRequest);

    Page<AuthorReport> getAllAuthor(Pageable pageable);

}
