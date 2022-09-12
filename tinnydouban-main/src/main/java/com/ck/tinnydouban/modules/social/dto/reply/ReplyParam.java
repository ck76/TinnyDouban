package com.ck.tinnydouban.modules.social.dto.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ck.tinnydouban.pojo.entity.Reply;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@ToString
public class ReplyParam {


    private Long id;

    @NotNull
    @JsonProperty("reply_type")
    private Integer replyType;

    @JsonProperty("comment_id")
    private Long commentId;

    @JsonProperty("reply_id")
    private Long replyId;

    @NotNull
    @JsonProperty("reply_user_id")
    private Long replyUserId;

    @NotBlank
    private String content;
}
