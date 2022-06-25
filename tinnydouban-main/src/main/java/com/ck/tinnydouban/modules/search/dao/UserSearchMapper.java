package com.ck.tinnydouban.modules.search.dao;

import com.ck.tinnydouban.pojo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;


public interface UserSearchMapper extends ElasticsearchRepository<User, Integer> {

    List<User> findByNameLike(String name, Pageable pageable);

}
