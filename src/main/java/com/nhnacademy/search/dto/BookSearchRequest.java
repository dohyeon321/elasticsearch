package com.nhnacademy.search.dto;

import com.nhnacademy.search.domain.SearchCondition;
import com.nhnacademy.search.domain.SortCondition;
import lombok.Data;

@Data
public class BookSearchRequest {
    private String keyword;
    private SearchCondition searchCondition;
    private SortCondition sortCondition;
    private int page = 0;
    private int size = 10;
}
