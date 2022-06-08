package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieFilmmaker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MovieFilmmakerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieFilmmaker record);

    MovieFilmmaker selectByPrimaryKey(Long id);

    List<MovieFilmmaker> selectByMovie(Long movieId);

    List<MovieFilmmaker> selectByFilmmaker(Long filmmakerId);

    List<MovieFilmmaker> selectAll();

    int updateByPrimaryKey(MovieFilmmaker record);

    int deleteAll();
}
