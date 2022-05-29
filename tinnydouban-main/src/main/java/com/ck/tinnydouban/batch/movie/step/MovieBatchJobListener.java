package com.ck.tinnydouban.batch.movie.step;

import com.ck.tinnydouban.modules.movie.service.CategoryService;
import com.ck.tinnydouban.modules.movie.service.LanguageService;
import com.ck.tinnydouban.modules.movie.service.MovieService;
import com.ck.tinnydouban.modules.movie.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.annotation.Resource;


@Slf4j
public class MovieBatchJobListener implements JobExecutionListener {


    @Resource
    private MovieService movieService;

    @Resource
    private RegionService regionService;

    @Resource
    private LanguageService languageService;

    @Resource
    private CategoryService categoryService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("movie before job !");

//        movieService.deleteAll();
//        movieService.deleteAllMovieFilmmaker();
//        regionService.deleteAll();
//        regionService.deleteAllMovieRegion();
//        languageService.deleteAll();
//        languageService.deleteAllMovieLanguage();
//        categoryService.deleteAll();
//        categoryService.deleteAllMovieCategory();

        log.info("clear all table done !");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {


        log.info("movie data insert job done !");
    }
}
