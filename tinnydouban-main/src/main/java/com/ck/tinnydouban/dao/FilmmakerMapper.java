package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Filmmaker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FilmmakerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Filmmaker record);

    Filmmaker selectByPrimaryKey(Long id);


    Filmmaker selectByDoubanId(Long dbId);

    List<Filmmaker> selectAll();

    int updateByPrimaryKey(Filmmaker record);

    int deleteAll();
}
