package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieLanguage;

import java.util.List;

public interface MovieLanguageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieLanguage record);

    MovieLanguage selectByPrimaryKey(Long id);

    List<MovieLanguage> selectAll();

    int updateByPrimaryKey(MovieLanguage record);
}
