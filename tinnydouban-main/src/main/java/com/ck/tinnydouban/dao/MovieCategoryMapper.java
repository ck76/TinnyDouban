package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MovieCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieCategory record);

    MovieCategory selectByPrimaryKey(Long id);

    List<MovieCategory> selectAll();

    List<MovieCategory> selectByMovie(Long movieId);

    List<MovieCategory> selectByCategory(Long categoryId);

    int updateByPrimaryKey(MovieCategory record);

    int deleteAll();
}
