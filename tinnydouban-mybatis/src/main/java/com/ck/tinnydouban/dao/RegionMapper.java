package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Region;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Region record);

    Region selectByPrimaryKey(Long id);

    List<Region> selectAll();

    int updateByPrimaryKey(Region record);
}
