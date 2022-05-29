package com.ck.tinnydouban.batch.movie.step;

import com.ck.tinnydouban.batch.movie.dto.MovieBatchDto;
import com.ck.tinnydouban.batch.movie.dto.MovieExtraDto;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class MovieBatchProcessor implements ItemProcessor<MovieBatchDto, MovieBatchDto> {


    @Override
    public MovieBatchDto process(MovieBatchDto dto) throws Exception {


//        log.info(dto.toString());
        dto.setMovieModel(parseMovieModel(dto));
        dto.setMovieExtraDto(parseMovieExtra(dto));
        return dto;
    }


    private Movie parseMovieModel(MovieBatchDto dto) {

        Movie movie = new Movie();
        movie.setName(dto.getName());
        movie.setDoubanId(dto.getMovieId());
        movie.setAlias(dto.getAlias());
        movie.setCoverUrl(dto.getCover());
        movie.setDoubanScore(dto.getDoubanScore());
//        movie.setDoubanVote(Integer.valueOf(dto.getDoubanVotes()));
        movie.setMins(dto.getMins());
        movie.setYear(dto.getYear());
        movie.setStoryline(dto.getStoryline());
        movie.setTags(dto.getTags());
        // 发行日期
        String releaseTime = dto.getReleaseDate();
        if (!StringUtils.isEmpty(releaseTime)) {
            movie.setReleaseDate(DateUtil.formatSlashStr2Date(releaseTime));
        }
        return movie;
    }


    private MovieExtraDto parseMovieExtra(MovieBatchDto dto) {
        MovieExtraDto movieExtra = new MovieExtraDto();

        String genres = dto.getGenres();
        if (!StringUtils.isEmpty(genres)) {
            List<String> list = new ArrayList<>(Arrays.asList(genres.split("/")));
            movieExtra.setCategoryList(list.stream().map(String::trim).collect(Collectors.toList()));
        }

        String languages = dto.getLanguages();
        if (!StringUtils.isEmpty(languages)) {
            List<String> list = new ArrayList<>(Arrays.asList(languages.split("/")));
            movieExtra.setLanguageList(list.stream().map(String::trim).collect(Collectors.toList()));
        }

        String regions = dto.getRegions();
        if (!StringUtils.isEmpty(regions)) {
            List<String> list = new ArrayList<>(Arrays.asList(regions.split("/")));
            movieExtra.setRegionList(list.stream().map(String::trim).collect(Collectors.toList()));
        }

        String actors = dto.getActorIds();
        if (!StringUtils.isEmpty(actors)) {
            List<String> list = new ArrayList<>(Arrays.asList(actors.split("[|]")));
            movieExtra.setActorIdList(list.stream()
                    .filter(s -> s.lastIndexOf(":") > -1)
                    .filter(s -> s.lastIndexOf(":") < s.length() - 1)
                    .map(s -> s.substring(s.lastIndexOf(":") + 1).trim())
                    .map(Long::valueOf)
                    .collect(Collectors.toList()));
        }

        String directorIds = dto.getDirectorIds();
        if (!StringUtils.isEmpty(directorIds)) {
            List<String> list = new ArrayList<>(Arrays.asList(directorIds.split("[|]")));
            movieExtra.setDirectorIdList(list.stream()
                    .filter(s -> s.lastIndexOf(":") > -1)
                    .filter(s -> s.lastIndexOf(":") < s.length() - 1)
                    .map(s -> s.substring(s.lastIndexOf(":") + 1).trim())
                    .map(Long::valueOf)
                    .collect(Collectors.toList()));
        }


        return movieExtra;
    }
}
