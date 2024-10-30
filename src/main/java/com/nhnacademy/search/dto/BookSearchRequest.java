package com.nhnacademy.search.dto;

import lombok.Data;

@Data
public class BookSearchRequest {
    private String keyword;
    private String searchCondition; // 예: "title", "description", "author"
    private String sortCondition;   // 예: "popularity", "newest", "lowest_price"
    private int page = 0;
    private int size = 10;
}
