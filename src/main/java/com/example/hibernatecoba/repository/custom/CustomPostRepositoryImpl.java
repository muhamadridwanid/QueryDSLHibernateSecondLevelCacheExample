package com.example.hibernatecoba.repository.custom;

import com.example.hibernatecoba.common.CommonUtils;
import com.example.hibernatecoba.dto.response.PostReport2;
import com.example.hibernatecoba.entity.QAuthor;
import com.example.hibernatecoba.entity.QPost;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<PostReport2> findReportAllFilter(Pageable pageable, Long id, String title, String description, String content, String authorName) {
        QPost post = QPost.post;
        QAuthor author = QAuthor.author;

        JPAQuery<PostReport2> query = new JPAQuery<>(entityManager);
        query.select(Projections.bean(PostReport2.class,
                post.id.as("id"),
                post.title.as("title"),
                post.description.as("description"),
                post.content.as("content"),
                post.date.as("date"),
                author.firstName.concat(" ").concat(author.lastName).as("authorName"))
        ).from(post)
                .leftJoin(author).on(post.author.id.eq(author.id))
                .setHint(QueryHints.HINT_CACHEABLE, true);

        //where clause
        applyWhereClause(query, id, title, description, content, authorName);

        //total
        long total = query.fetchCount();

        //apply sorting
        CommonUtils.applySortingQueryDSL(query, pageable);

        //set limit offset untuk list
        query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        //get list berdasarkan limit
        List<PostReport2> list = query.fetch();

        return new PageImpl<>(list, pageable, total);
    }

    private void applyWhereClause(JPAQuery<PostReport2> queryList, Long id, String title, String description, String content, String authorName) {

        QPost post = QPost.post;
        QAuthor author = QAuthor.author;

        if (id != null) {
            queryList.where(post.id.eq(id));
        }

        if (title != null && title.equals("")) {
            queryList.where(post.title.like(changeAllStar(title)));
        }

        if (description != null && description.equals("")) {
            queryList.where(post.description.like(changeAllStar(description)));
        }

        if (content != null && content.equals("")) {
            queryList.where(post.content.like(changeAllStar(content)));
        }

        if (authorName != null && authorName.equals("")) {
            queryList.where(author.firstName.concat(author.lastName).like(changeAllStar(authorName)));
        }
    }

    private String changeAllStar(String s) {
        return s.replaceAll("\\*", "%");
    }

}
