package com.nhnacademy.search.service.impl;

import com.nhnacademy.search.domain.document.BookDocument;
import com.nhnacademy.search.dto.BookSearchRequest;
import com.nhnacademy.search.dto.BookSearchResponse;
import com.nhnacademy.search.repository.BookSearchRepository;
import com.nhnacademy.search.repository.BookRepository;
import com.nhnacademy.search.repository.BookDocumentRepository;
import com.nhnacademy.search.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {

    private final BookSearchRepository bookSearchRepository;
//    private final BookRepository bookRepository;  // 추가
//    private final BookDocumentRepository bookDocumentRepository;  // 추가

    @Override
    public Page<BookSearchResponse> searchBooks(Pageable pageable, BookSearchRequest searchRequest) {
        Page<BookDocument> bookDocuments = bookSearchRepository.search(
                pageable,
                searchRequest.getKeyword(),
                searchRequest.getSearchCondition(),
                searchRequest.getSortCondition()
        );

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

//    @Scheduled(fixedDelay = 30 * 1000)
//    @Transactional
//    public void updateBookIndex() {
//        LocalDateTime thirtySecondsAgo = LocalDateTime.now().minusSeconds(30);
//
//        List<Book> updatedBooks = bookRepository.findRecentlyModifiedBooks(thirtySecondsAgo);
//        List<BookDocument> updatedBookDocuments = updatedBooks.stream()
//                .map(this::bookToBookDocument)
//                .collect(Collectors.toList());
//        bookDocumentRepository.saveAll(updatedBookDocuments);
//    }
//
//    private BookDocument bookToBookDocument(Book book) {
//        return BookDocument.builder()
//                .id(book.getId())
//                .title(book.getTitle())
//                .description(book.getDescription())
//                .contributor(book.getContributors())
//                .publisherName(book.getPublisherName())
//                .pubDate(book.getPubDate())
//                .price(book.getPrice())
//                .discountRate(book.getDiscountRate())
//                .build();
//    }
}
