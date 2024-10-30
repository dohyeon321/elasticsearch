package com.nhnacademy.search.domain.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Document(indexName = "books")
public class BookDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String authorNames;

    @Field(type = FieldType.Keyword)
    private String isbn;

    @Field(type = FieldType.Date)
    private LocalDate publication;

    @Field(type = FieldType.Keyword)
    private String publisherName;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Double)
    private BigDecimal discountPrice;

    @Field(type = FieldType.Long)
    private Long popularity;

    @Field(type = FieldType.Text)
    private List<String> tags;  // 태그 리스트

    @Field(type = FieldType.Integer)
    private Integer reviewCount;

    @Field(type = FieldType.Double)
    private Double averageRating;
}
