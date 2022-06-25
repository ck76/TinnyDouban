package com.ck.tinnydouban.modules.search.service.impl;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.service.MovieService;
import com.ck.tinnydouban.modules.search.dao.MovieSearchMapper;
import com.ck.tinnydouban.modules.search.dao.PostSearchMapper;
import com.ck.tinnydouban.modules.search.dao.UserSearchMapper;
import com.ck.tinnydouban.modules.search.pojo.SearchVO;
import com.ck.tinnydouban.modules.search.service.SearchService;
import com.ck.tinnydouban.modules.security.dto.vo.UserVO;
import com.ck.tinnydouban.modules.social.dto.post.PostVO;
import com.ck.tinnydouban.modules.social.service.LikeService;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.Post;
import com.ck.tinnydouban.pojo.entity.User;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Resource
    private MovieSearchMapper movieSearchMapper;

    @Resource
    private PostSearchMapper postSearchMapper;

    @Resource
    private UserSearchMapper userSearchMapper;


    @Resource
    private MovieService movieService;

    @Resource
    private LikeService likeService;


    @Override
    public void saveMovie(Movie movie) {
        movieSearchMapper.save(movie);
    }


    /**
     * 聚合全文查询
     * <p>
     * 包含电影、帖子、用户,
     * <p>
     * 可以指定数量，默认每种类型查询5个, 不分页
     * <p>
     * 用于统一展示使用，每种类别的分页查询提供单独接口
     *
     * @param keyword 查询关键字
     * @return 聚合体
     */
    @Override
    public SearchVO unionSearch(String keyword, Integer count) {

        SearchVO vo = new SearchVO();
        vo.setMovieList(movieSearch(keyword, 0, count).getList());
        vo.setPostList(postSearch(keyword, 0, count).getList());
        vo.setUserList(userSearch(keyword, 0, count).getList());

        return vo;
    }

    @Override
    public PageInfo<MovieVO> movieSearch(String keyword, Integer offset, Integer count) {

        PageRequest pageRequest = PageRequest.of(offset, count);
        List<Movie> movies = movieSearchMapper.findByNameOrStoryline(keyword, keyword, pageRequest);

        List<MovieVO> voList = movies.stream().map(movie -> {
            try {
                return movieService.wrapMovie(movie, false);
            } catch (ApiException e) {
//                log.error(e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return new PageInfo<>(voList);
    }

    @Override
    public PageInfo<PostVO> postSearch(String keyword, Integer offset, Integer count) {
        PageRequest pageRequest = PageRequest.of(offset, count);

        List<Post> posts = postSearchMapper.findByTitleOrContent(keyword, keyword, pageRequest);

        List<PostVO> voList = posts.stream().map(post -> {
            try {

                PostVO vo = new PostVO();
                BeanUtils.copyProperties(post, vo);
                vo.setLikeNum(likeService.queryPostLikeNum(post.getId()));
                return vo;

            } catch (Exception e) {
//                log.error(e.getMessage());
                return null;
            }


        }).filter(Objects::nonNull).collect(Collectors.toList());
        return new PageInfo<>(voList);
    }

    @Override
    public PageInfo<UserVO> userSearch(String keyword, Integer offset, Integer count) {
        PageRequest pageRequest = PageRequest.of(offset, count);
        List<User> users = userSearchMapper.findByNameLike(keyword, pageRequest);

        List<UserVO> voList = users.stream().map(user -> {
            try {

                UserVO vo = new UserVO();
                BeanUtils.copyProperties(user, vo);
                return vo;

            } catch (Exception e) {
//                log.error(e.getMessage());
                return null;
            }

        }).filter(Objects::nonNull).collect(Collectors.toList());

        return new PageInfo<>(voList);
    }


}
