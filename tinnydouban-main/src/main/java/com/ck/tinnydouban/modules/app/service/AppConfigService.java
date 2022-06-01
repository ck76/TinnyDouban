package com.ck.tinnydouban.modules.app.service;


import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.app.pojo.AppConfigDTO;
import com.ck.tinnydouban.modules.app.pojo.AppConfigVO;

import java.util.List;


public interface AppConfigService {


    void createVersionConfig(AppConfigDTO dto) throws ApiException;

    List<AppConfigVO> queryAllVersion();

    AppConfigVO lastVersion();


}
