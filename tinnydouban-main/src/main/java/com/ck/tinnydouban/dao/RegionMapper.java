package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Region record);

    Region selectByPrimaryKey(Long id);

    List<Region> selectAll();

    int updateByPrimaryKey(Region record);

    int deleteAll();
}
