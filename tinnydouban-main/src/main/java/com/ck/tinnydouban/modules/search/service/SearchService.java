package com.ck.tinnydouban.modules.search.service;

import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.search.pojo.SearchVO;
import com.ck.tinnydouban.modules.security.dto.vo.UserVO;
import com.ck.tinnydouban.modules.social.dto.post.PostVO;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.github.pagehelper.PageInfo;

public interface SearchService {


    SearchVO unionSearch(String keyword, Integer count);

    PageInfo<MovieVO> movieSearch(String keyword, Integer offset, Integer count);

    PageInfo<PostVO> postSearch(String keyword, Integer offset, Integer count);

    PageInfo<UserVO> userSearch(String keyword, Integer offset, Integer count);


    void saveMovie(Movie movie);

}
