package com.nhnacademy.search.dto;

import com.nhnacademy.search.domain.SearchCondition;
import com.nhnacademy.search.domain.SortCondition;
import lombok.Data;

@Data
public class BookSearchRequest {
    private String keyword;
    private SearchCondition searchCondition;
    private SortCondition sortCondition;
    private int page = 0; // 현재 페이지 번호,  디폴트:0번째 페이지
    private int size = 10; //한 페이지에 표시할 결과 수
}
