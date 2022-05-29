package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieCategory;

import java.util.List;

public interface MovieCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieCategory record);

    MovieCategory selectByPrimaryKey(Long id);

    List<MovieCategory> selectAll();

    int updateByPrimaryKey(MovieCategory record);
}
