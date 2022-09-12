package com.ck.tinnydouban.modules.social.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class CommentParam {

    private Long id;

    @JsonProperty("post_id")
    private Long postId;

    private String content;
}
