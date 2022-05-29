package com.ck.tinnydouban.modules.movie.dto.language;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LanguageVO {

    private Long id;

    @JsonProperty("language_name")
    private String languageName;
}
