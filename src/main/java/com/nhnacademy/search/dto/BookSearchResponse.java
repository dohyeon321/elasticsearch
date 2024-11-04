package com.nhnacademy.search.dto;
import com.nhnacademy.search.domain.Contributor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class BookSearchResponse {
    private Long id;
    private String thumbnail;
    private String title;
    private String description;
    private List<Contributor> contributor; //기여자
    private String publisherName; //출판사
    private LocalDate publication;
    private BigDecimal price;
    private float discountRate;
    private int reviewCount;
    private int reviewScore;
}
