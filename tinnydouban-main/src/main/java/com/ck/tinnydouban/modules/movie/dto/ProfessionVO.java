package com.ck.tinnydouban.modules.movie.dto;



import com.ck.tinnydouban.modules.movie.dto.filmmaker.FilmmakerVO;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ProfessionVO {

    private Long id;

    private String name;

    /* 当前岗位有哪些演职人员 */
    private List<FilmmakerVO> filmmakers = new ArrayList<>();


    public void addFilmmakerVO(FilmmakerVO filmmakerVO) {
        filmmakers.add(filmmakerVO);
    }




}
