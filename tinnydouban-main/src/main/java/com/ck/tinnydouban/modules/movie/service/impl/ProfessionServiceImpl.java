package com.ck.tinnydouban.modules.movie.service.impl;

import com.ck.tinnydouban.dao.FilmmakerProfessionMapper;
import com.ck.tinnydouban.dao.ProfessionMapper;
import com.ck.tinnydouban.modules.movie.dto.ProfessionVO;
import com.ck.tinnydouban.modules.movie.service.ProfessionService;
import com.ck.tinnydouban.pojo.entity.Filmmaker;
import com.ck.tinnydouban.pojo.entity.FilmmakerProfession;
import com.ck.tinnydouban.pojo.entity.Profession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.peer.PanelPeer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Resource
    private ProfessionMapper professionMapper;

    @Resource
    private FilmmakerProfessionMapper filmmakerProfessionMapper;


    /**
     * 插入一种职业
     *
     * @param professionName
     * @return
     */
    @Override
    public Long insert(String professionName) {

        Profession profession = new Profession();
        profession.setName(professionName);
        profession.setCreatedTime(new Date(System.currentTimeMillis()));
        profession.setUpdatedTime(new Date(System.currentTimeMillis()));
        professionMapper.insert(profession);

        return profession.getId();
    }

    /**
     * 给一个演职员关联一种职业
     *
     * @param filmmaker
     * @param professionId
     * @return
     */
    @Override
    public int insertFilmmakerProfession(Filmmaker filmmaker, Long professionId) {

        FilmmakerProfession filmmakerProfession = new FilmmakerProfession();
        filmmakerProfession.setFilmmakerId(filmmaker.getId());
        filmmakerProfession.setFilmmakerDoubanId(filmmaker.getDoubanId());
        filmmakerProfession.setProfessionId(professionId);
        filmmakerProfession.setCreatedTime(new Date(System.currentTimeMillis()));
        filmmakerProfession.setUpdatedTime(new Date(System.currentTimeMillis()));

        return filmmakerProfessionMapper.insert(filmmakerProfession);
    }

    @Override
    public int deleteAll() {
        return professionMapper.deleteAll();
    }

    @Override
    public int deleteAllFilmmakerProfession() {
        return filmmakerProfessionMapper.deleteAll();
    }

    @Override
    public Profession queryByName(String name) {
        return professionMapper.selectByName(name);
    }

    /**
     * 查询电影中所包含的职业
     * @param movieId
     * @return
     */
    @Override
    public List<ProfessionVO> queryByMovie(Long movieId) {

        List<ProfessionVO> professionList = new ArrayList<>();
//        List<Filmmaker> filmmakerList = filmmakerProfessionMapper
        return professionList;
    }


    @Override
    public Profession queryById(Long id) {
        return professionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(Profession profession) {
        return 0;
    }

    @Override
    public List<Profession> queryAll() {
        return null;
    }
}
