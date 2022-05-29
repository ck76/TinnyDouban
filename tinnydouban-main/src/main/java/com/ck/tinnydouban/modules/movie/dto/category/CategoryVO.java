package com.ck.tinnydouban.modules.movie.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryVO {

    private Long id;

    @JsonProperty("category_name")
    private String categoryName;
}
