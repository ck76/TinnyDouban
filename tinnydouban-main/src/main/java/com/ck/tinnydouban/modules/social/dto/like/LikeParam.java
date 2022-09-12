package com.ck.tinnydouban.modules.social.dto.like;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@ToString
public class LikeParam {

    @NotNull
    private Integer type;

    private Long postId;

    private Long commentId;

    @NotNull
    private Long userId;
}
