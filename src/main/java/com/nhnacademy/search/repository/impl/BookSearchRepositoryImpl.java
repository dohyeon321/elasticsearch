package com.nhnacademy.search.repository.impl;

import com.nhnacademy.search.domain.SortCondition;
import com.nhnacademy.search.domain.document.BookDocument;
import com.nhnacademy.search.domain.SearchCondition;
import com.nhnacademy.search.repository.BookSearchRepository;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.core.query.Query;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.SearchHits;

@Repository
public class BookSearchRepositoryImpl implements BookSearchRepository {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    public BookSearchRepositoryImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Page<BookDocument> search(Pageable pageable, String keyword, SearchCondition searchCondition, SortCondition sortCondition) {
        var queryBuilder = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery(searchCondition.name().toLowerCase(), keyword))
                .withPageable(pageable);

        // Sorting based on SortCondition enum
        switch (sortCondition) {
            case POPULARITY:
                queryBuilder.withSort(SortBuilders.fieldSort("popularity").order(SortOrder.DESC));
                break;
            case NEWEST:
                queryBuilder.withSort(SortBuilders.fieldSort("publishDate").order(SortOrder.DESC));
                break;
            case LOWEST_PRICE:
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
                break;
            case HIGHEST_PRICE:
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
                break;
            case RATING:
                queryBuilder.withSort(SortBuilders.fieldSort("rating").order(SortOrder.DESC));
                break;
            case REVIEW_COUNT:
                queryBuilder.withSort(SortBuilders.fieldSort("reviewCount").order(SortOrder.DESC));
                break;
        }

        Query query = queryBuilder.build();
        SearchHits<BookDocument> searchHits = elasticsearchTemplate.search(query, BookDocument.class);

        return PageableExecutionUtils.getPage(searchHits.map(SearchHit::getContent).toList(), pageable, searchHits::getTotalHits);
    }
}
