package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.modules.movie.dto.ProfessionVO;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.pojo.entity.Language;
import com.ck.tinnydouban.pojo.entity.Profession;

import java.util.List;


public interface ProfessionService {

    Long insert(String professionName);

    int insertFilmmakerProfession(Filmmaker filmmaker, Long professionId);

    int deleteAll();

    int deleteAllFilmmakerProfession();

    Profession queryByName(String name);

    List<ProfessionVO> queryByMovie(Long movieId);



    Profession queryById(Long id);

    int deleteById(Long id);

    int update(Profession profession);

    List<Profession> queryAll();
}
