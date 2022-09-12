package com.ck.tinnydouban.modules.social.dto.like;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Data
@ToString
public class LikeNumDTO {

    private Integer type;

    private Long postId;

    private Long commentId;

    private Long likeNum;
}
