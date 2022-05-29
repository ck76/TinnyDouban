package com.ck.tinnydouban.batch.filmmaker.step;

import com.ck.tinnydouban.modules.movie.service.FilmmakerService;
import com.ck.tinnydouban.modules.movie.service.ProfessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.annotation.Resource;


@Slf4j
public class FilmmakerBatchJobListener implements JobExecutionListener {

    @Resource
    private FilmmakerService filmmakerService;

    @Resource
    private ProfessionService professionService;



    @Override
    public void beforeJob(JobExecution jobExecution) {

//        filmmakerService.deleteAll();
//        professionService.deleteAll();
//        professionService.deleteAllFilmmakerProfession();

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
