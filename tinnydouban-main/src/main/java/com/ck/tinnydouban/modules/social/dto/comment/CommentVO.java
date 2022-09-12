package com.ck.tinnydouban.modules.social.dto.comment;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CommentVO {

    private Long id;

    private Long postId;

    private Long userId;

    private Long likeNum;

    private Long replyNum;

    private String content;
}
