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
@Document(indexName = "titty_post")
public class Post implements Serializable {
    private static final long serialVersionUID = 3865474734159634899L;
    @Id
    private Long id;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Long, enabled = false)
    private Long topicId;

    @Field(type = FieldType.Boolean, enabled = false)
    private Boolean postType;

    @Field(type = FieldType.Long, enabled = false)
    private Long movieId;

    @Field(type = FieldType.Long, enabled = false)
    private Long createUserId;

    @Field(type = FieldType.Long, enabled = false)
    private Long likeNum;

    @Field(type = FieldType.Long, enabled = false)
    private Long commentNum;

    @Field(type = FieldType.Date, enabled = false)
    private Date lastCommentTime;

    @Field(type = FieldType.Date, enabled = false)
    private Date createdTime;

    @Field(type = FieldType.Date, enabled = false)
    private Date updatedTime;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;


}
