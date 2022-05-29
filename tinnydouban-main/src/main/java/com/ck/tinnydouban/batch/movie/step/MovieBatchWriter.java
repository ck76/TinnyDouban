package com.ck.tinnydouban.batch.movie.step;

import com.ck.tinnydouban.batch.helper.BatchDataHelper;
import com.ck.tinnydouban.batch.movie.dto.MovieBatchDto;
import com.ck.tinnydouban.batch.movie.dto.MovieExtraDto;
import com.ck.tinnydouban.modules.movie.service.*;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.Profession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
public class MovieBatchWriter implements ItemWriter<MovieBatchDto> {


    @Resource
    private MovieService movieService;

    @Resource
    private RegionService regionService;

    @Resource
    private LanguageService languageService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private FilmmakerService filmmakerService;

    @Resource
    private ProfessionService professionService;


    @Override
    public void write(List<? extends MovieBatchDto> list) throws Exception {

        for (MovieBatchDto batchDto : list) {

            Movie movieModel = batchDto.getMovieModel();
            MovieExtraDto movieExtra = batchDto.getMovieExtraDto();

            log.info(movieModel.toString());
            log.info(movieExtra.toString());

            // 插入后得到id
            movieService.insert(movieModel);
            // 添加 Category Language Region 实体和相应的关系
            addCategory(movieExtra.getCategoryList(), movieModel);
            addLanguage(movieExtra.getLanguageList(), movieModel);
            addRegion(movieExtra.getRegionList(), movieModel);


            // 插入演员关系
            List<Long> actorIdList = movieExtra.getActorIdList();
            if (!CollectionUtils.isEmpty(actorIdList)) {
                for (Long doubanId : actorIdList) {
                    Filmmaker filmmaker = filmmakerService.queryByDoubanId(doubanId);
                    Profession profession = professionService.queryByName("演员");
                    movieService.insertMovieFilmmaker(movieModel, filmmaker, profession);
                }
            }

            // 插入导演关系
            List<Long> directorIdList = movieExtra.getDirectorIdList();
            if (!CollectionUtils.isEmpty(directorIdList)) {
                for (Long doubanId : directorIdList) {
                    Filmmaker filmmaker = filmmakerService.queryByDoubanId(doubanId);
                    Profession profession = professionService.queryByName("导演");
                    movieService.insertMovieFilmmaker(movieModel, filmmaker, profession);
                }
            }

        }

        log.debug("batch size : " + list.size());
    }


    private void addCategory(List<String> categoryList, Movie movie) {

        if (CollectionUtils.isEmpty(categoryList))
            return;


        for (String categoryStr : categoryList) {
            Long id = BatchDataHelper.getInstance().hasCategory(categoryStr);
            if (id == -1) {
                id = categoryService.insert(categoryStr);
                BatchDataHelper.getInstance().addCategory(categoryStr, id);
            }
            categoryService.insertMovieCategory(movie, id);
        }
    }

    private void addLanguage(List<String> languageList, Movie movie) {


        if (CollectionUtils.isEmpty(languageList))
            return;
        for (String languageStr : languageList) {
            Long id = BatchDataHelper.getInstance().hasLanguage(languageStr);
            if (id == -1) {
                id = languageService.insert(languageStr);
                BatchDataHelper.getInstance().addLanguage(languageStr, id);
            }
            languageService.insertMovieLanguage(movie, id);

        }
    }

    private void addRegion(List<String> regionList, Movie movie) {

        if (CollectionUtils.isEmpty(regionList))
            return;
        for (String regionStr : regionList) {
            Long id = BatchDataHelper.getInstance().hasRegion(regionStr);
            if (id == -1) {
                id = regionService.insert(regionStr);
                BatchDataHelper.getInstance().addRegion(regionStr, id);
            }
            regionService.insertMovieRegion(movie, id);
        }
    }
}
