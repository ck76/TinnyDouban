package com.ck.tinnydouban.modules.movie.service.impl;

import com.ck.tinnydouban.dao.MovieMapper;
import com.ck.tinnydouban.dao.MovieUserMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.service.MovieService;
import com.ck.tinnydouban.modules.movie.service.MovieUserService;
import com.ck.tinnydouban.modules.security.service.UserService;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.MovieUser;
import com.ck.tinnydouban.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class MovieUserServiceImpl implements MovieUserService {


    @Resource
    private MovieUserMapper movieUserMapper;

    @Resource
    private MovieService movieService;


    @Resource
    private MovieMapper movieMapper;


    @Resource
    private UserService userService;


    MovieUser selectMovieUser(Long movieId) throws ApiException {

        Long uid = userService.currentUserId();
        Movie movie = movieMapper.selectByPrimaryKey(movieId);
        ApiException.when(movie == null, "电影不存在");
        MovieUser movieUser = movieUserMapper.selectByUserMovie(uid, movieId);

        if (movieUser == null) {
            movieUser = new MovieUser();
            movieUser.setUserId(uid);
            movieUser.setMovieId(movieId);
            movieUser.setHaveSeen(false);
            movieUser.setWannaSee(false);
            movieUser.setScore(0.0);
            movieUser.setMovieDoubanId(movie.getDoubanId());
            movieUser.setCreatedTime(DateUtil.currentDate());
            movieUser.setUpdatedTime(DateUtil.currentDate());
            movieUserMapper.insert(movieUser);
        }

        return movieUser;
    }


    @Override
    public void wannaSeeMovie(Long movieId) throws ApiException {

        MovieUser movieUser = selectMovieUser(movieId);
        ApiException.when(movieUser.getHaveSeen(), "该电影已经看过");

        movieUser.setWannaSee(true);
        movieUser.setUpdatedTime(DateUtil.currentDate());
        movieUser.setWannaSeeTime(DateUtil.currentDate());
        movieUserMapper.updateByPrimaryKey(movieUser);
    }

    @Override
    public void cancelWannaSeeMovie(Long movieId) throws ApiException {

        MovieUser movieUser = selectMovieUser(movieId);


        ApiException.when(movieUser.getHaveSeen(), "该电影已经看过");

        movieUser.setWannaSee(false);
        movieUser.setUpdatedTime(DateUtil.currentDate());
        movieUser.setWannaSeeTime(DateUtil.currentDate());
        movieUserMapper.updateByPrimaryKey(movieUser);
    }

    @Override
    public void haveSeenMovie(Long movieId) throws ApiException {

        MovieUser movieUser = selectMovieUser(movieId);
        movieUser.setHaveSeen(true);
        movieUser.setWannaSee(false); // 看过后从想看的电影中移除
        movieUser.setUpdatedTime(DateUtil.currentDate());
        movieUser.setHaveSeenTime(DateUtil.currentDate());
        movieUserMapper.updateByPrimaryKey(movieUser);
    }

    @Override
    public void ratingMovie(Long movieId, Double score) throws ApiException {

        MovieUser movieUser = selectMovieUser(movieId);
        movieUser.setWannaSee(false); // 看过后从想看的电影中移除
        movieUser.setHaveSeen(true); // 打分自动设置为看过

        // 先更新电影平均分数
        updateMovieScore(movieId, movieUser.getScore(), score);

        movieUser.setScore(score);
        movieUser.setUpdatedTime(DateUtil.currentDate());
        movieUser.setHaveSeenTime(DateUtil.currentDate());
        movieUser.setScoreTime(DateUtil.currentDate());
        movieUserMapper.updateByPrimaryKey(movieUser);
    }

    private void updateMovieScore(Long movieId, Double originScore, Double newScore) {

        // 更新电影的打分信息

        // 若已经打过分, 则需要从原来的分数中减去再更新
        Movie movie = movieMapper.selectByPrimaryKey(movieId);

        Double movieScore = movie.getDoubanScore();
        Long voteNum = Long.valueOf(movie.getDoubanVote());
        double totalScore = movieScore * voteNum; // 整体分数
        double doubanScore = newScore * 2; // 将5分制转化为10分制

        // 当前用户没有对该电影打过分
        if (originScore == 0) {

            voteNum++;
            totalScore += doubanScore;
        } else {
            totalScore = totalScore - originScore * 2 + doubanScore;
        }
        Double newAvgScore = totalScore / voteNum;

        movie.setDoubanVote(Math.toIntExact(voteNum));
        movie.setDoubanScore(newAvgScore);
        movie.setUpdatedTime(DateUtil.currentDate());
        movieMapper.updateByPrimaryKey(movie);
    }


    @Override
    public PageInfo<MovieVO> queryAllWannaSee(Integer offset, Integer count) throws ApiException {

        PageHelper.offsetPage(offset, count);
        Long uid = userService.currentUserId();
        List<MovieUser> movieUsers = movieUserMapper.selectAllWannaSeeByUid(uid);
        return wrapPageInfo(movieUsers);
    }

    @Override
    public PageInfo<MovieVO> queryAllHaveSee(Integer offset, Integer count) throws ApiException {
        PageHelper.offsetPage(offset, count);
        Long uid = userService.currentUserId();
        List<MovieUser> movieUsers = movieUserMapper.selectAllHaveSeenByUid(uid);
        return wrapPageInfo(movieUsers);
    }

    private PageInfo<MovieVO> wrapPageInfo(List<MovieUser> list) throws ApiException {
        List<MovieVO> voList = new ArrayList<>();
        for (MovieUser mu : list) {
            MovieVO vo = movieService.queryById(mu.getMovieId(), false);
            voList.add(vo);
        }
        return new PageInfo<>(voList);
    }

}
