package com.ck.tinnydouban.modules.search.dao;

import com.ck.tinnydouban.pojo.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface MovieSearchMapper extends ElasticsearchRepository<Movie, Long> {

    List<Movie> findByName(String name);

    List<Movie> findByStorylineLike(String storyline);

    List<Movie> findByNameOrStoryline(String name,String storyline, Pageable pageable);
}
