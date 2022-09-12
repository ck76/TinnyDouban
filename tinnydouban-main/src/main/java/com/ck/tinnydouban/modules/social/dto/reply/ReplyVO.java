package com.ck.tinnydouban.modules.social.dto.reply;

import com.ck.tinnydouban.pojo.entity.Reply;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class ReplyVO {

    private Long id;

    private Integer replyType;

    private Long commentId;

    private Long replyId;

    private Long userId;

    private Long replyUserId;

    private Boolean status;

    private String content;
}
