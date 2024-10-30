package com.nhnacademy.search.repository;

import com.nhnacademy.search.domain.document.BookDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookSearchRepository {
    Page<BookDocument> search(Pageable pageable, String keyword, String searchCondition, String sortCondition);
}
