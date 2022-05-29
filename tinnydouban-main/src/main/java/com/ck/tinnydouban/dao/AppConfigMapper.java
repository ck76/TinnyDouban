package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.AppConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppConfig record);

    AppConfig selectByPrimaryKey(Long id);

    AppConfig selectByVersion(String versionCode);

    List<AppConfig> selectAll();

    int updateByPrimaryKey(AppConfig record);
}
