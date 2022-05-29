package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.filmmaker.FilmmakerVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.pojo.entity.Category;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.MovieFilmmaker;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface FilmmakerService {

    int insert(Filmmaker filmmaker);

    int deleteAll();

    Filmmaker queryByDoubanId(Long dbId);

    FilmmakerVO queryById(Long id, Boolean withMovieInfo) throws ApiException;

    PageInfo<MovieVO> queryMoviesByFilmmaker(Long filmmakerId, Integer offset, Integer count) throws ApiException;


    int deleteById(Long id);

    int update(Filmmaker filmmaker);

    List<Filmmaker> queryAll();


    List<MovieFilmmaker> queryMovieFilmmaker(Long movieId);
}
