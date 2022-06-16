package com.ck.tinnydouban.modules.app.pojo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 设置一些版本号信息，还有下载地址，更新信息，用作提示
 */
@Data
@ToString
public class AppConfigDTO {

    @NotNull
    private Integer mainVersionCode;

    @NotNull
    private Integer subVersionCode;

    @NotNull
    private Integer grayVersionCode;

    @NotBlank
    private String resourceUrl;

    @NotBlank
    private String updateInfo;
}
