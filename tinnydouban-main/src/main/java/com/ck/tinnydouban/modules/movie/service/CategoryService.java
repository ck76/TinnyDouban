package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.category.CategoryVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.pojo.entity.Category;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {

    Long insert(String categoryName);


    int insertMovieCategory(Movie movie, Long categoryId);

    int deleteAll();

    int deleteAllMovieCategory();


    List<CategoryVO> queryByMovie(Long movieId);


    Category queryById(Long id);

    int deleteById(Long id);

    int update(Category category);

    List<CategoryVO> queryAll();

    PageInfo<MovieVO> queryMovieByCategory(Long categoryId, Integer offset, Integer count) throws ApiException;
}

