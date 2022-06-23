package com.ck.tinnydouban.modules.movie.service.impl;

import com.ck.tinnydouban.dao.MovieFilmmakerMapper;
import com.ck.tinnydouban.dao.MovieMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.filmmaker.FilmmakerVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.dto.ProfessionVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieParam;
import com.ck.tinnydouban.modules.movie.service.*;
import com.ck.tinnydouban.pojo.entity.*;
import com.ck.tinnydouban.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;



@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieMapper movieMapper;


    @Resource
    private MovieFilmmakerMapper movieFilmmakerMapper;


    @Resource
    private LanguageService languageService;


    @Resource
    private RegionService regionService;


    @Resource
    private CategoryService categoryService;


    @Resource
    private FilmmakerService filmmakerService;


    @Resource
    private ProfessionService professionService;


    /**
     * 插入电影信息
     *
     * @return
     */
    @Override
    public int insert(MovieParam qvo) {

        Movie movie = new Movie();
        BeanUtils.copyProperties(qvo, movie);
        movie.setReleaseDate(DateUtil.formatStr2Date(
                DateUtil.FORMAT_ACROSS, qvo.getReleaseDate()));
        movie.setCreatedTime(new Date(System.currentTimeMillis()));
        movie.setUpdatedTime(new Date(System.currentTimeMillis()));
        return movieMapper.insert(movie);
    }

    @Override
    public int insert(Movie movie) {
        movie.setCreatedTime(new Date(System.currentTimeMillis()));
        movie.setUpdatedTime(new Date(System.currentTimeMillis()));
        return movieMapper.insert(movie);
    }


    /**
     * 根据id查询电影
     *
     * @param id
     * @return
     * @throws ApiException
     */
    @Override
    public MovieVO queryById(Long id, Boolean withActorInfo) throws ApiException {

        Movie movie = movieMapper.selectByPrimaryKey(id);
        MovieVO movieVO = wrapMovie(movie, withActorInfo);
        ApiException.when(movieVO == null, "电影查询失败");
        return movieVO;
    }

    /**
     * 根据豆瓣id查询电影
     *
     * @param dbId
     * @return
     * @throws ApiException
     */
    @Override
    public MovieVO queryByDoubanId(Long dbId, Boolean withActorInfo) throws ApiException {

        Movie movie = movieMapper.selectByDoubanId(dbId);
        MovieVO movieVO = wrapMovie(movie, withActorInfo);
        ApiException.when(movieVO == null, "电影查询失败");
        return movieVO;
    }

    /**
     * 包裹movie
     * 填充演职人员信息
     *
     * @param movie
     * @param withDetailInfo
     * @return
     * @throws ApiException
     */
    @Override
    public MovieVO wrapMovie(Movie movie, Boolean withDetailInfo) throws ApiException {

        if (movie == null)
            return null;

        MovieVO movieVO = new MovieVO();
        BeanUtils.copyProperties(movie, movieVO);
        if (!withDetailInfo) {
            return movieVO;
        }


        movieVO.setLanguages(languageService.queryByMovie(movie.getId()));
        movieVO.setCategories(categoryService.queryByMovie(movie.getId()));
        movieVO.setRegions(regionService.queryByMovie(movie.getId()));

        // 先获取当前电影下的全部演职人员信息
        List<MovieFilmmaker> movieFilmmakerList = filmmakerService.queryMovieFilmmaker(movie.getId());
        List<ProfessionVO> professionVOList = new ArrayList<>();


        for (MovieFilmmaker mf : movieFilmmakerList) {
            // 获取具体的演员和职业信息
            FilmmakerVO filmmakerVO = filmmakerService.queryById(mf.getFilmmakerId(),false);
            Profession profession = professionService.queryById(mf.getProfessionId());
            ProfessionVO professionVO = checkProfession(professionVOList, profession);

            if (profession == null) {
                continue;
            }

            if (professionVO == null) {
                professionVO = new ProfessionVO();
                BeanUtils.copyProperties(profession, professionVO);
                professionVOList.add(professionVO);
            }

            professionVO.addFilmmakerVO(filmmakerVO);
        }

        movieVO.setProfessions(professionVOList);
        return movieVO;
    }



    private ProfessionVO checkProfession(List<ProfessionVO> professionVOList, Profession profession) {

        if (CollectionUtils.isEmpty(professionVOList)) {
            return null;
        }

        for (ProfessionVO professionVO : professionVOList) {
            if ((profession.getId().equals(professionVO.getId()))) {
                return professionVO;
            }
        }
        return null;
    }


    /**
     * 删除全部电影
     *
     * @return
     */
    @Override
    public int deleteAll() {
        return movieMapper.deleteAll();
    }


    /**
     * 删除指定电影
     *
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id) throws ApiException {

        ApiException.when(movieMapper.deleteByPrimaryKey(id) == 0,
                "删除失败");
    }


    /**
     * 更新电影
     *
     * @return
     */
    @Override
    public int update(MovieParam qvo) throws ApiException {

        Movie movie = movieMapper.selectByPrimaryKey(qvo.getId());
        ApiException.when(movie == null, "当前信息不存在");

        BeanUtils.copyProperties(qvo, movie);
        movie.setReleaseDate(DateUtil.formatStr2Date(
                DateUtil.FORMAT_ACROSS, qvo.getReleaseDate()));
        movie.setUpdatedTime(new Date(System.currentTimeMillis()));
        return movieMapper.updateByPrimaryKey(movie);
    }


    /**
     * 给电影插入演职人员信息
     *
     * @param movie
     * @param filmmaker
     * @param profession
     * @return
     */
    @Override
    public int insertMovieFilmmaker(Movie movie, Filmmaker filmmaker, Profession profession) {

        if (movie == null || filmmaker == null || profession == null) {
            log.error("电影-演员关系插入失败");
            return 0;
        }

        MovieFilmmaker movieFilmmaker = new MovieFilmmaker();

        movieFilmmaker.setMovieId(movie.getId());
        movieFilmmaker.setMovieDoubanId(movie.getDoubanId());
        movieFilmmaker.setFilmmakerId(filmmaker.getId());
        movieFilmmaker.setFilmmakerDoubanId(filmmaker.getDoubanId());
        movieFilmmaker.setProfessionId(profession.getId());
        movieFilmmaker.setCreatedTime(new Date(System.currentTimeMillis()));
        movieFilmmaker.setUpdatedTime(new Date(System.currentTimeMillis()));

        return movieFilmmakerMapper.insert(movieFilmmaker);
    }

    /**
     * 给电影删除所有演职人员信息
     */
    @Override
    public int deleteAllMovieFilmmaker() {
        return movieFilmmakerMapper.deleteAll();
    }


    @Override
    public List<Movie> queryAll() {
        return movieMapper.selectAll();
    }


    /**
     * 根据年查询电影
     *
     * @param year
     * @param offset
     * @param count
     * @return
     * @throws ApiException
     */
    @Override
    public PageInfo<MovieVO> queryMovieByYear(Integer year, Integer offset, Integer count) throws ApiException {

        PageHelper.offsetPage(offset, count);
        List<Movie> movies = movieMapper.selectByYear(year);

        List<MovieVO> voList = new ArrayList<>();
        for (Movie movie : movies) {
            voList.add(wrapMovie(movie, false));
        }
        return new PageInfo<>(voList);
    }

    /**
     * 只查询有封面的，mock数据
     *
     * @param offset
     * @param count
     * @return
     * @throws ApiException
     */
    @Override
    public PageInfo<MovieVO> queryMovieWithCover(Integer offset, Integer count) throws ApiException {
        PageHelper.offsetPage(offset, count);
        List<Movie> movies = movieMapper.selectHaveCover();

        List<MovieVO> voList = new ArrayList<>();
        for (Movie movie : movies) {
            voList.add(wrapMovie(movie, false));
        }
        return new PageInfo<>(voList);
    }


}
