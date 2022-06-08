package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieRegion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MovieRegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieRegion record);

    MovieRegion selectByPrimaryKey(Long id);

    List<MovieRegion> selectAll();

    List<MovieRegion> selectByMovie(Long movieId);

    List<MovieRegion> selectByRegion(Long regionId);

    int updateByPrimaryKey(MovieRegion record);

    int deleteAll();
}
