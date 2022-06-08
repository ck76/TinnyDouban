package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieLanguage;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MovieLanguageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieLanguage record);

    MovieLanguage selectByPrimaryKey(Long id);

    List<MovieLanguage> selectByMovie(Long movieId);

    List<MovieLanguage> selectByLanguage(Long languageId);

    List<MovieLanguage> selectAll();

    int updateByPrimaryKey(MovieLanguage record);

    int deleteAll();
}
