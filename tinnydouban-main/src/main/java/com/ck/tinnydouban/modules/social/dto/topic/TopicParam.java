package com.ck.tinnydouban.modules.social.dto.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ck.tinnydouban.base.BaseQVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@ToString
public class TopicParam extends BaseQVO {

    Long id;

    @NotBlank
    private String name;

    @JsonProperty("topic_abstract")
    private String topicAbstract;

    @ApiParam("话题类型: 0为常规话题，1为电影话题")
    @JsonProperty("topic_type")
    private Integer topicType;

    @JsonProperty("movie_id")
    private Long movieId;
}
