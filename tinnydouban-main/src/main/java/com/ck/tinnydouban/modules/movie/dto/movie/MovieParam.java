package com.ck.tinnydouban.modules.movie.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ck.tinnydouban.base.BaseQVO;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@ToString
public class MovieParam extends BaseQVO {

    private Long id;

    @NotBlank
    @ApiParam("豆瓣ID")
    @JsonProperty("douban_id")
    private Long doubanId;

    @NotBlank
    @ApiParam("电影名称")
    private String name;

    @ApiParam("电影别名")
    private String alias;

    @ApiParam("电影封面地址")
    @JsonProperty("cover_url")
    private String coverUrl;

    @ApiParam("电影评分")
    @JsonProperty("douban_score")
    private Double doubanScore;

    @ApiParam("电影投票数量")
    @JsonProperty("douban_vote")
    private Long doubanVote;

    @ApiParam("电影时长")
    private Integer mins;

    @ApiParam("电影发布时间")
    @JsonProperty("release_date")
    private String releaseDate;

    @ApiParam("电影上映年份")
    private Integer year;

    @ApiParam("电影简介")
    private String storyline;

    @ApiParam("电影标签")
    private String tags;

}
