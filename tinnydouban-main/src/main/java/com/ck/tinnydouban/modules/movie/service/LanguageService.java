package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.language.LanguageVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.pojo.entity.Language;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface LanguageService {

    Long insert(String name);


    int insertMovieLanguage(Movie movie, Long languageId);

    int deleteAll();

    int deleteAllMovieLanguage();

    Language queryById(Long id);


    List<LanguageVO> queryByMovie(Long id);


    int deleteById(Long id);

    int update(Language language);

    List<LanguageVO> queryAll();

    PageInfo<MovieVO> queryMovieByLanguage(Long languageId, Integer offset, Integer count) throws ApiException;
}
