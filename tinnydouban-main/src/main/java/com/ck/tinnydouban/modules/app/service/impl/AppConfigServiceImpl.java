package com.ck.tinnydouban.modules.app.service.impl;

import com.ck.tinnydouban.dao.AppConfigMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.app.pojo.AppConfigDTO;
import com.ck.tinnydouban.modules.app.pojo.AppConfigVO;
import com.ck.tinnydouban.modules.app.service.AppConfigService;
import com.ck.tinnydouban.pojo.entity.AppConfig;
import com.ck.tinnydouban.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Resource
    private AppConfigMapper appConfigMapper;

    @Override
    public void createVersionConfig(AppConfigDTO dto) throws ApiException {

        String versionCode = dto.getMainVersionCode() + "." +
                dto.getSubVersionCode() + "." + dto.getGrayVersionCode();

        AppConfig appConfig = appConfigMapper.selectByVersion(versionCode);
        ApiException.when(appConfig != null, "当前版本号已经存在");

        appConfig = new AppConfig();
        BeanUtils.copyProperties(dto, appConfig);
        appConfig.setVersionCode(versionCode);
        appConfig.setCreatedTime(DateUtil.currentDate());
        appConfig.setUpdatedTime(DateUtil.currentDate());

        ApiException.when(appConfigMapper.insert(appConfig) == 0, "插入失败");
    }

    @Override
    public List<AppConfigVO> queryAllVersion() {
        List<AppConfig> configList = querySortVersion();
        return configList.stream()
                .map(appConfig -> {
                    AppConfigVO vo = new AppConfigVO();
                    BeanUtils.copyProperties(appConfig, vo);
                    vo.setUpdatedTime(appConfig.getUpdatedTime().toString());
                    return vo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public AppConfigVO lastVersion() {
        List<AppConfig> configList = querySortVersion();
        AppConfig config = configList.get(0);
        AppConfigVO vo = new AppConfigVO();
        BeanUtils.copyProperties(config, vo);
        vo.setUpdatedTime(config.getUpdatedTime().toString());
        return vo;
    }

    private List<AppConfig> querySortVersion() {

        List<AppConfig> configList = appConfigMapper.selectAll();

        configList.sort((o1, o2) -> {

            if (o1.getMainVersionCode().equals(o2.getMainVersionCode())) {
                if (o1.getSubVersionCode().equals(o2.getSubVersionCode())) {
                    return o2.getGrayVersionCode() - o1.getGrayVersionCode();
                } else {
                    return o2.getSubVersionCode() - o1.getSubVersionCode();
                }
            }
            return o2.getMainVersionCode() - o1.getMainVersionCode();
        });
        return configList;
    }
}
