package com.ck.tinnydouban.batch.filmmaker.dto;

import com.ck.tinnydouban.pojo.entity.Filmmaker;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FilmmakerBatchDto {


    private Long personId;

    private String name;

    private String sex;

    private String nameEn;

    private String nameZh;

    private String birth;

    private String birthPlace;

    private String constellation;

    private String profession;

    private String biography;

    private Filmmaker filmmaker;

    private FilmmakerExtraDto filmmakerExtraDto;

}
