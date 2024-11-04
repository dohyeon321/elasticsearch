package com.nhnacademy.search.service;

import com.nhnacademy.search.dto.BookSearchRequest;
import com.nhnacademy.search.dto.BookSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookSearchService {
    Page<BookSearchResponse> searchBooks(Pageable pageable, BookSearchRequest searchRequest);
}



