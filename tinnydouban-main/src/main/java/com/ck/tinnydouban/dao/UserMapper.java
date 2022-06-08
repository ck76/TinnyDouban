package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    User selectByPrimaryKey(Integer id);

    User selectByPhone(String phone);

    List<User> selectAll();

    int updateByPrimaryKey(User user);

    List<User> selectNum(Integer num);
}
