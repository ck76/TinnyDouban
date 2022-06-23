package com.ck.tinnydouban.modules.movie.service.impl;

import com.ck.tinnydouban.dao.FilmmakerMapper;
import com.ck.tinnydouban.dao.MovieFilmmakerMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.filmmaker.FilmmakerVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.service.FilmmakerService;
import com.ck.tinnydouban.modules.movie.service.MovieService;
import com.ck.tinnydouban.modules.movie.service.ProfessionService;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.pojo.entity.MovieFilmmaker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 演员相关
 */
@Slf4j
@Service
public class FilmmakerServiceImpl implements FilmmakerService {


    @Resource
    private FilmmakerMapper filmmakerMapper;


    @Resource
    private MovieFilmmakerMapper movieFilmmakerMapper;


    @Resource
    private ProfessionService professionService;

    @Resource
    private MovieService movieService;


    /**
     * 插入演员
     */
    @Override
    public int insert(Filmmaker filmmaker) {

        filmmaker.setCreatedTime(new Date(System.currentTimeMillis()));
        filmmaker.setUpdatedTime(new Date(System.currentTimeMillis()));
        return filmmakerMapper.insert(filmmaker);
    }

    @Override
    public int deleteAll() {
        return filmmakerMapper.deleteAll();
    }

    /**
     * 演员有演员id和豆瓣id
     *
     * 电影有豆瓣id，演员也有豆瓣id，不冲突，因为数据是从豆瓣爬来的
     */
    @Override
    public Filmmaker queryByDoubanId(Long dbId) {
        return filmmakerMapper.selectByDoubanId(dbId);
    }


    /**
     * 根据演员id查询演员，id是本身数据库生成的id
     *
     * @param id
     * @param withMovieInfo
     * @return
     * @throws ApiException
     */
    @Override
    public FilmmakerVO queryById(Long id, Boolean withMovieInfo) throws ApiException {

        Filmmaker filmmaker = filmmakerMapper.selectByPrimaryKey(id);
        FilmmakerVO vo = wrapFilmmaker(filmmaker, withMovieInfo);
        ApiException.when(vo == null, "演员查询失败");
        return vo;
    }

    /**
     * 根据演员查询他出演的作品
     *
     * @param filmmakerId
     * @param offset
     * @param count
     * @return
     * @throws ApiException
     */
    @Override
    public PageInfo<MovieVO> queryMoviesByFilmmaker(Long filmmakerId, Integer offset, Integer count) throws ApiException {

        PageHelper.offsetPage(offset, count);
        List<MovieFilmmaker> movieFilmmakerList = movieFilmmakerMapper.selectByFilmmaker(filmmakerId);
        List<MovieVO> movieVOList = new ArrayList<>();

        for (MovieFilmmaker mf : movieFilmmakerList) {
            MovieVO vo = movieService.queryById(mf.getMovieId(), false);
            movieVOList.add(vo);
        }
        return new PageInfo<>(movieVOList);
    }

    //如果有作品，那么把作品填充到包装类
    FilmmakerVO wrapFilmmaker(Filmmaker filmmaker, Boolean withMovieInfo) throws ApiException {

        if (filmmaker == null) {
            return null;
        }

        FilmmakerVO filmmakerVO = new FilmmakerVO();
        BeanUtils.copyProperties(filmmaker, filmmakerVO);

        if (!withMovieInfo) {
            return filmmakerVO;
        }

        //如果有作品，那么把作品填充到包装类
        List<MovieFilmmaker> movieFilmmakerList = movieFilmmakerMapper.selectByFilmmaker(filmmaker.getId());
        List<MovieVO> movieVOList = new ArrayList<>();

        for (MovieFilmmaker mf : movieFilmmakerList) {
            MovieVO movieVO = movieService.queryById(mf.getMovieId(), false);
            if (movieVO != null) {
                movieVOList.add(movieVO);
            }
        }

        filmmakerVO.setMovies(movieVOList);
        return filmmakerVO;
    }


    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(Filmmaker filmmaker) {
        return 0;
    }

    @Override
    public List<Filmmaker> queryAll() {
        return null;
    }

    /**
     * 演员相关
     */
    @Override
    public List<MovieFilmmaker> queryMovieFilmmaker(Long movieId) {
        return movieFilmmakerMapper.selectByMovie(movieId);
    }
}
