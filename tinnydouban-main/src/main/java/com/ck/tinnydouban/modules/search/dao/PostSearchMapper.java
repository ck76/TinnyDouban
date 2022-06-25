package com.ck.tinnydouban.modules.search.dao;

import com.ck.tinnydouban.pojo.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostSearchMapper extends ElasticsearchRepository<Post, Long> {

    List<Post> findByTitleOrContent(String title, String content, Pageable pageable);

}
