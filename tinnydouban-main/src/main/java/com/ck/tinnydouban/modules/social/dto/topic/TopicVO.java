package com.ck.tinnydouban.modules.social.dto.topic;

import com.ck.tinnydouban.base.BasePVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TopicVO extends BasePVO {

    private Long id;

    private String name;

    private Long createUserId;

    private Long postNum;

    private Long userNum;

    private String topicAbstract;
}
