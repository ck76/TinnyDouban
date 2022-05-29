package com.ck.tinnydouban.modules.movie.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.dto.region.RegionVO;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.Region;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface RegionService {

    Long insert(String regionName);

    int insertMovieRegion(Movie movie, Long regionId);

    int deleteAll();

    int deleteAllMovieRegion();

    Region queryById(Long id);

    int deleteById(Long id);

    int update(Region region);

    List<RegionVO> queryAll();

    List<RegionVO> queryByMovie(Long movieId);

    PageInfo<MovieVO> queryMovieByRegion(Long regionId, Integer offset, Integer count) throws ApiException;
}
