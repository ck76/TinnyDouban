package com.ck.tinnydouban.modules.social.dto.post;

import lombok.Data;
import lombok.ToString;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.Date;


@Data
@ToString
public class PostVO {


    private Long id;

    private String title;

    private Long topicId;

    private Boolean postType;

    private Long movieId;

    private Long createUserId;

    private Long likeNum;

    private Long commentNum;

    private Date lastCommentTime;

    private String content;

}
