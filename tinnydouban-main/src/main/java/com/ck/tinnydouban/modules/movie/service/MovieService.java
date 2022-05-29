package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieParam;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.Profession;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface MovieService {

    int insert(MovieParam qvo);

    int insert(Movie movie);

    int deleteAll();

    int insertMovieFilmmaker(Movie movie, Filmmaker filmmaker, Profession profession);


    int deleteAllMovieFilmmaker();


    MovieVO queryById(Long id, Boolean withActorInfo) throws ApiException;

    MovieVO queryByDoubanId(Long dbId, Boolean withActorInfo) throws ApiException;

    PageInfo<MovieVO> queryMovieByYear(Integer year, Integer offset, Integer count) throws ApiException;

    PageInfo<MovieVO> queryMovieWithCover(Integer offset, Integer count) throws ApiException;


    void deleteById(Long id) throws ApiException;

    int update(MovieParam qvo) throws ApiException;

    List<Movie> queryAll();

    MovieVO wrapMovie(Movie movie, Boolean withActorInfo) throws ApiException;

}
