package com.ck.tinnydouban.batch.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.batch.service.BatchService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/batch")
public class BatchController {


    @Resource
    private BatchService batchService;



    @GetMapping("/movie")
    CommonResult movieBatchJob() throws ApiException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ApiException.when(!batchService.executeMovieBatchJob(), "批处理执行失败");
        return CommonResult.success();
    }


    @GetMapping("/filmmaker")
    CommonResult filmmakerBatchJob() throws ApiException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ApiException.when(!batchService.executeFilmmakerBatchJob(), "批处理执行失败");
        return CommonResult.success();
    }




}
