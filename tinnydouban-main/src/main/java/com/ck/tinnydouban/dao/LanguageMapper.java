package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Language;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LanguageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Language record);

    Language selectByPrimaryKey(Long id);

    List<Language> selectAll();

    int updateByPrimaryKey(Language record);

    int deleteAll();
}
