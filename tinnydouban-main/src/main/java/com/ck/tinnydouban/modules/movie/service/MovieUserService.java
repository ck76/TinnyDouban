package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.github.pagehelper.PageInfo;


public interface MovieUserService {


    void wannaSeeMovie(Long movieId) throws ApiException;

    void cancelWannaSeeMovie(Long movieId) throws ApiException;

    void haveSeenMovie(Long movieId) throws ApiException;

    void ratingMovie(Long movieId, Double score) throws ApiException;

    PageInfo<MovieVO> queryAllWannaSee(Integer offset, Integer count) throws ApiException;

    PageInfo<MovieVO> queryAllHaveSee(Integer offset, Integer count) throws ApiException;

}
