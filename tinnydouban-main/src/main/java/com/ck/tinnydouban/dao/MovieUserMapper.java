package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.MovieUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieUser record);

    MovieUser selectByPrimaryKey(Long id);

    MovieUser selectByUserMovie(@Param("uid") Long uid, @Param("mid") Long mid);

    List<MovieUser> selectAll();

    int updateByPrimaryKey(MovieUser record);


    List<MovieUser> selectAllWannaSeeByUid(Long uid);

    List<MovieUser> selectAllHaveSeenByUid(Long uid);
}
