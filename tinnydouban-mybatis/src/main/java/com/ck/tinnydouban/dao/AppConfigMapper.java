package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.AppConfig;

import java.util.List;

public interface AppConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppConfig record);

    AppConfig selectByPrimaryKey(Long id);

    List<AppConfig> selectAll();

    int updateByPrimaryKey(AppConfig record);
}
