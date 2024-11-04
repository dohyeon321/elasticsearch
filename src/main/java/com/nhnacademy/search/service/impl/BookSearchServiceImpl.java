package com.nhnacademy.search.service.impl;

import com.nhnacademy.search.domain.document.BookDocument;
import com.nhnacademy.search.dto.BookSearchRequest;
import com.nhnacademy.search.dto.BookSearchResponse;
import com.nhnacademy.search.repository.BookSearchRepository;
import com.nhnacademy.search.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {

    private final BookSearchRepository bookSearchRepository;

    @Override
    public Page<BookSearchResponse> searchBooks(Pageable pageable, BookSearchRequest searchRequest) {
        // Elasticsearch 쿼리 실행 (BookSearchRepository에 위임)
        Page<BookDocument> bookDocuments = bookSearchRepository.search(
                pageable,
                searchRequest.getKeyword(),
                searchRequest.getSearchCondition(),
                searchRequest.getSortCondition()
        );

        // 결과를 BookSearchResponse로 매핑
        return bookDocuments.map(document -> BookSearchResponse.builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .contributor(document.getContributors())
                .publisherName(document.getPublisherName())
                .pubDate(LocalDate.parse(document.getPubDate().toString()))
                .price(document.getPrice())
                .discountRate(document.getDiscountRate())
                .build());
    }
}
