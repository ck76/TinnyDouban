package com.ck.tinnydouban.batch.filmmaker;


import com.ck.tinnydouban.batch.filmmaker.dto.FilmmakerBatchDto;
import com.ck.tinnydouban.batch.filmmaker.step.FilmmakerBatchJobListener;
import com.ck.tinnydouban.batch.filmmaker.step.FilmmakerBatchProcessor;
import com.ck.tinnydouban.batch.filmmaker.step.FilmmakerBatchWriter;
import com.ck.tinnydouban.batch.filmmaker.step.FilmmakerFieldBatchMapper;
import com.ck.tinnydouban.batch.movie.dto.MovieBatchDto;
import com.ck.tinnydouban.batch.movie.step.MovieBatchJobListener;
import com.ck.tinnydouban.batch.movie.step.MovieBatchProcessor;
import com.ck.tinnydouban.batch.movie.step.MovieBatchWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.XStreamExecutionContextStringSerializer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;

//@Configuration
@EnableBatchProcessing
@Slf4j
public class FilmmakerBatchConfig {


    @Resource
    public JobBuilderFactory jobBuilderFactory;

    @Resource
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job filmmakerBatchJob(Step filmmakerBatchStep, JobExecutionListener filmmakerBatchJobListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory
                .get(funcName)
                .listener(filmmakerBatchJobListener)
                .flow(filmmakerBatchStep)
                .end()
                .build();
    }

    @Bean
    public Step filmmakerBatchStep(ItemReader<FilmmakerBatchDto> filmmakerCsvReader,
                               ItemProcessor<FilmmakerBatchDto, FilmmakerBatchDto> filmmakerBatchProcessor,
                               ItemWriter<FilmmakerBatchDto> filmmakerCsvWriter) {

        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory
                .get(funcName)
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {

//                        log.info("movie before step !");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        log.info("movie after step !");
                        return ExitStatus.COMPLETED;
                    }
                })
                .<FilmmakerBatchDto, FilmmakerBatchDto>chunk(1000)
                .reader(filmmakerCsvReader)
                .processor(filmmakerBatchProcessor)
                .writer(filmmakerCsvWriter)
                .build();
    }

    @Bean
    public ItemReader<FilmmakerBatchDto> filmmakerCsvReader() {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return new FlatFileItemReaderBuilder<FilmmakerBatchDto>()
                .name(funcName)
//                .resource(new ClassPathResource("dataset/mini_person.csv"))
                .resource(new ClassPathResource("dataset/person.csv"))
                .linesToSkip(1) // 跳过首行
                .delimited()
                .names(fieldNames())
                .fieldSetMapper(new FilmmakerFieldBatchMapper())
                .build();
    }


    @Bean
    public ItemProcessor<FilmmakerBatchDto, FilmmakerBatchDto> filmmakerBatchProcessor() {
        return new FilmmakerBatchProcessor();
    }


    @Bean
    public ItemWriter<FilmmakerBatchDto> filmmakerCsvWriter() {
        return new FilmmakerBatchWriter();
    }


    @Bean
    public JobExecutionListener filmmakerBatchJobListener() {
        return new FilmmakerBatchJobListener();
    }





    public static String[] fieldNames() {
        return new String[]{
                "PERSON_ID", "NAME", "SEX", "NAME_EN", "NAME_ZH",
                "BIRTH", "BIRTHPLACE", "CONSTELLATORY", "PROFESSION", "BIOGRAPHY"
        };
    }
}
