package com.ck.tinnydouban.modules.app.pojo;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Data
@ToString
public class AppConfigVO {

    private Long id;

    private String versionCode;

    private String resourceUrl;

    private String updatedTime;

    private String updateInfo;
}
