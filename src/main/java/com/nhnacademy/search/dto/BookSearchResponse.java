package com.nhnacademy.search.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookSearchResponse {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String pubDate;
    private double price;
    private double discountPrice;
    private long popularity;
}
