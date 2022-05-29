package com.ck.tinnydouban.batch.movie;



import com.ck.tinnydouban.batch.movie.dto.MovieBatchDto;
import com.ck.tinnydouban.batch.movie.step.MovieBatchJobListener;
import com.ck.tinnydouban.batch.movie.step.MovieBatchProcessor;
import com.ck.tinnydouban.batch.movie.step.MovieBatchWriter;
import com.ck.tinnydouban.batch.movie.step.MovieFieldBatchMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
public class MovieBatchConfig {


    @Resource
    public JobBuilderFactory jobBuilderFactory;

    @Resource
    public StepBuilderFactory stepBuilderFactory;

    @Bean // 带有参数的bean注解, 若有多个实例, 按照方法名称注入
    public Job movieBatchJob(Step movieBatchStep, JobExecutionListener movieBatchJobListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory
                .get(funcName)
                .listener(movieBatchJobListener)
                .flow(movieBatchStep)
                .end()
                .build();
    }


    @Bean
    public Step movieBatchStep(ItemReader<MovieBatchDto> movieCsvReader,
                               ItemProcessor<MovieBatchDto, MovieBatchDto> movieBatchProcessor,
                               ItemWriter<MovieBatchDto> movieCsvWriter) {

        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory
                .get(funcName)
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {

                        log.info("movie before step !");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        log.info("movie after step !");
                        return ExitStatus.COMPLETED;
                    }
                })
                .<MovieBatchDto, MovieBatchDto>chunk(1000)
                .reader(movieCsvReader)
                .processor(movieBatchProcessor)
                .writer(movieCsvWriter)
                .build();
    }


    @Bean
    public ItemReader<MovieBatchDto> movieCsvReader() {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return new FlatFileItemReaderBuilder<MovieBatchDto>()
                .name(funcName)
//                .resource(new ClassPathResource("dataset/mini_movie.csv"))
                .resource(new ClassPathResource("dataset/movies.csv"))
                .linesToSkip(1) // 跳过首行
                .delimited()
                .names(fieldNames())
                .fieldSetMapper(new MovieFieldBatchMapper())
                .build();
    }

    @Bean
    public ItemProcessor<MovieBatchDto, MovieBatchDto> movieBatchProcessor() {
        return new MovieBatchProcessor();
    }


    @Bean
    public ItemWriter<MovieBatchDto> movieCsvWriter() {
        return new MovieBatchWriter();
    }


    @Bean
    public JobExecutionListener movieBatchJobListener() {
        return new MovieBatchJobListener();
    }


    public static String[] fieldNames() {
        return new String[]{
                "MOVIE_ID", "NAME", "ALIAS", "ACTORS", "COVER",
                "DIRECTORS", "DOUBAN_SCORE", "DOUBAN_VOTES", "GENRES", "IMDB_ID",
                "LANGUAGES", "MINS", "OFFICIAL_SITE", "REGIONS", "RELEASE_DATE",
                "SLUG", "STORYLINE", "TAGS", "YEAR", "ACTOR_IDS", "DIRECTOR_IDS"
        };
    }

}
