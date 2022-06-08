package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProfessionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Profession record);

    Profession selectByPrimaryKey(Long id);

    Profession selectByName(String name);

    List<Profession> selectAll();

    int updateByPrimaryKey(Profession record);

    int deleteAll();
}
