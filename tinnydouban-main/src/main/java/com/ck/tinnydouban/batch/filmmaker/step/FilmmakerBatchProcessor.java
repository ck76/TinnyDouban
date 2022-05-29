package com.ck.tinnydouban.batch.filmmaker.step;

import com.ck.tinnydouban.batch.filmmaker.dto.FilmmakerBatchDto;
import com.ck.tinnydouban.batch.filmmaker.dto.FilmmakerExtraDto;
import com.ck.tinnydouban.batch.movie.dto.MovieBatchDto;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class FilmmakerBatchProcessor implements ItemProcessor<FilmmakerBatchDto, FilmmakerBatchDto> {

    @Override
    public FilmmakerBatchDto process(FilmmakerBatchDto dto) throws Exception {

//        log.info(dto.toString());
        dto.setFilmmaker(parseFilmmaker(dto));
        dto.setFilmmakerExtraDto(parseFilmmakerExtra(dto));
        return dto;
    }


    private Filmmaker parseFilmmaker(FilmmakerBatchDto dto) {

        Filmmaker filmmaker = new Filmmaker();

        filmmaker.setDoubanId(dto.getPersonId());
        filmmaker.setSex(dto.getSex());
        filmmaker.setName(dto.getName());
        filmmaker.setNameEn(dto.getNameEn());
        filmmaker.setNameZh(dto.getNameZh());
        filmmaker.setConstellation(dto.getConstellation());
        filmmaker.setBirthPlace(dto.getBirthPlace());
        filmmaker.setBiography(dto.getBiography());
        // 出生日期
        String birthTime = dto.getBirth();
        if (!StringUtils.isEmpty(birthTime)) {
            filmmaker.setBirth(DateUtil.formatSlashStr2Date(birthTime));

        }

        return filmmaker;
    }


    private FilmmakerExtraDto parseFilmmakerExtra(FilmmakerBatchDto dto) {
        FilmmakerExtraDto filmmakerExtraDto = new FilmmakerExtraDto();

        String professionList = dto.getProfession();

        if (!StringUtils.isEmpty(professionList)) {
            List<String> list = new ArrayList<>(Arrays.asList(professionList.split("/")));
            filmmakerExtraDto.setProfessionList(list.stream().map(String::trim).collect(Collectors.toList()));
        }

        return filmmakerExtraDto;
    }

}
