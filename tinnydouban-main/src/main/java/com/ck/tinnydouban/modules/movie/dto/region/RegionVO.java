package com.ck.tinnydouban.modules.movie.dto.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegionVO {

    private Long id;

    @JsonProperty("region_name")
    private String regionName;
}
