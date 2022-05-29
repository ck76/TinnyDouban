package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Document(indexName = "titty_movie")
public class Movie implements Serializable {
    private static final long serialVersionUID = -1420290980296352145L;

    @Id
    @Field(type = FieldType.Long, enabled = true, index = false, store = true)
    private Long id;

    @Field(type = FieldType.Long, enabled = false)
    private Long doubanId;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String name;

    @Field(type = FieldType.Text, enabled = false)
    private String alias;

    @Field(type = FieldType.Text, enabled = false)
    private String coverUrl;

    @Field(type = FieldType.Double, enabled = false)
    private Double doubanScore;

    @Field(type = FieldType.Long, enabled = false)
    private Long doubanVote;

    @Field(type = FieldType.Integer, enabled = false)
    private Integer mins;

    @Field(type = FieldType.Date, enabled = false)
    private Date releaseDate;

    @Field(type = FieldType.Integer, enabled = false)
    private Integer year;

    @Field(type = FieldType.Date, enabled = false)
    private Date createdTime;

    @Field(type = FieldType.Date, enabled = false)
    private Date updatedTime;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String storyline;

    @Field(type = FieldType.Text, enabled = false)
    private String tags;
}
