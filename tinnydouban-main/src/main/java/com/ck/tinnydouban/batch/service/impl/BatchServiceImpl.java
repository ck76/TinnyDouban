package com.ck.tinnydouban.batch.service.impl;

import com.ck.tinnydouban.batch.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class BatchServiceImpl implements BatchService {

//    @Resource
//    private JobLauncher jobLauncher;
//
//    @Resource
//    private Job movieBatchJob;
//
//    @Resource
//    private Job filmmakerBatchJob;


    @Override
    public boolean executeMovieBatchJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        //构建参数
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        //执行任务
//        jobLauncher.run(movieBatchJob, jobParameters);

        return true;
    }

    @Override
    public boolean executeFilmmakerBatchJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
//        jobLauncher.run(filmmakerBatchJob, jobParameters);
        return true;
    }
}
