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
@Document(indexName = "titty_user")
public class User implements Serializable {


    private static final long serialVersionUID = -634026337147466383L;

    @Id
    private Integer id;

    @Field(type = FieldType.Text, enabled = false)
    private String phone;

    private String password;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String name;

    @Field(type = FieldType.Text, enabled = false)
    private String coverUrl;

    @Field(type = FieldType.Boolean, enabled = false)
    private Boolean status;

    @Field(type = FieldType.Date, enabled = false)
    private Date createdTime;

    @Field(type = FieldType.Date, enabled = false)
    private Date updatedTime;

}
