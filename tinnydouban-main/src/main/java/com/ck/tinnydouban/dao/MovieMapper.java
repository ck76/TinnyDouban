package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MovieMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Movie record);

    Movie selectByPrimaryKey(Long id);

    Movie selectByDoubanId(Long dbId);

    List<Movie> selectAll();

    List<Movie> selectByYear(Integer year);

    List<Movie> selectHaveCover();

    int updateByPrimaryKey(Movie record);

    int deleteAll();


    List<Movie> selectNum(Integer num);


}
