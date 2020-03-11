package com.example.hibernatecoba.common;

import com.example.hibernatecoba.dto.response.PostReport2;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {
    public static void applySortingQueryDSL(JPAQuery<PostReport2> query, Pageable pageable) {
        pageable.getSort().get().forEach(order -> {
            Order o = order.isAscending() ? Order.ASC : Order.DESC;
            OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(o, Expressions.stringPath(order.getProperty()));
            query.orderBy(orderSpecifier);
        });
    }

    public static Pageable pageableAllowedSort(Pageable pageable, List<String> cols) {
        Sort newSort = Sort.by(pageable.getSort()
                .get()
                .filter(order -> {
                    boolean r = false;
                    for (String s : cols) {
                        r = r || order.getProperty().equals(s);
                    }
                    return r;
                })
                .collect(Collectors.toList()));

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), newSort);

    }

}
