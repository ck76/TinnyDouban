package com.ck.tinnydouban.modules.movie.dto.filmmaker;

import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class FilmmakerVO {

    private Long id;

    private Long doubanId;

    private String name;

    private String nameEn;

    private String nameZh;

    private String sex;

    private String birth;

    private String birthPlace;

    private String constellation;

    private String biography;

    private List<MovieVO> movies;

}
