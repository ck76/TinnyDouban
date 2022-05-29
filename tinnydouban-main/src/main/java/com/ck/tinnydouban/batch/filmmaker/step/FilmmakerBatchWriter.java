package com.ck.tinnydouban.batch.filmmaker.step;

import com.ck.tinnydouban.batch.filmmaker.dto.FilmmakerBatchDto;
import com.ck.tinnydouban.batch.filmmaker.dto.FilmmakerExtraDto;
import com.ck.tinnydouban.batch.helper.BatchDataHelper;
import com.ck.tinnydouban.modules.movie.service.FilmmakerService;
import com.ck.tinnydouban.modules.movie.service.ProfessionService;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
public class FilmmakerBatchWriter implements ItemWriter<FilmmakerBatchDto> {

    @Resource
    private FilmmakerService filmmakerService;

    @Resource
    private ProfessionService professionService;



    @Override
    public void write(List<? extends FilmmakerBatchDto> list) throws Exception {

        for (FilmmakerBatchDto batchDto : list) {

            Filmmaker filmmaker = batchDto.getFilmmaker();
            FilmmakerExtraDto extraDto = batchDto.getFilmmakerExtraDto();

//            log.info(filmmaker.toString());
//            log.info(extraDto.toString());

            filmmakerService.insert(filmmaker);
            addProfession(extraDto.getProfessionList(), filmmaker);
        }


        log.debug("batch size : " + list.size());

    }

    private void addProfession(List<String> professionList, Filmmaker filmmaker) {

        if (CollectionUtils.isEmpty(professionList))
            return;

        for (String profession : professionList) {

            Long id = BatchDataHelper.getInstance().hasProfession(profession);
            if(id == -1) {
                id = professionService.insert(profession);
                BatchDataHelper.getInstance().addProfession(profession, id);
            }
            professionService.insertFilmmakerProfession(filmmaker, id);
        }
    }
}
