package com.nhnacademy.search.Controller;

import com.nhnacademy.search.dto.BookSearchRequest;
import com.nhnacademy.search.dto.BookSearchResponse;
import com.nhnacademy.search.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @PostMapping("/search")
    public Page<BookSearchResponse> searchBooks(@RequestBody BookSearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        return bookSearchService.searchBooks(pageRequest, searchRequest);
    }
}
