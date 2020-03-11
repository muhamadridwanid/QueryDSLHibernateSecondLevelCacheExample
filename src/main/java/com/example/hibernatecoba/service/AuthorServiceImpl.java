package com.example.hibernatecoba.service;

import com.example.hibernatecoba.dto.request.AuthorCreateRequest;
import com.example.hibernatecoba.dto.response.AuthorReport;
import com.example.hibernatecoba.entity.Author;
import com.example.hibernatecoba.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    @Override
    public void storeAuthor(AuthorCreateRequest createRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(createRequest, author);
        author.setAdded(new Date());
        authorRepository.save(author);
    }

    @Override
    public Page<AuthorReport> getAllAuthor(Pageable pageable) {
        return authorRepository.findAuthorReports(pageable);
    }
}
