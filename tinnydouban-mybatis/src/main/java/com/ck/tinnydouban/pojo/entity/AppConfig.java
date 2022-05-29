package com.ck.tinnydouban.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class AppConfig implements Serializable {
    private Long id;

    private Integer mainVersionCode;

    private Integer subVersionCode;

    private Integer grayVersionCode;

    private String versionCode;

    private String resourceUrl;

    private Date createdTime;

    private Date updatedTime;

    private String updateInfo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMainVersionCode() {
        return mainVersionCode;
    }

    public void setMainVersionCode(Integer mainVersionCode) {
        this.mainVersionCode = mainVersionCode;
    }

    public Integer getSubVersionCode() {
        return subVersionCode;
    }

    public void setSubVersionCode(Integer subVersionCode) {
        this.subVersionCode = subVersionCode;
    }

    public Integer getGrayVersionCode() {
        return grayVersionCode;
    }

    public void setGrayVersionCode(Integer grayVersionCode) {
        this.grayVersionCode = grayVersionCode;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo == null ? null : updateInfo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mainVersionCode=").append(mainVersionCode);
        sb.append(", subVersionCode=").append(subVersionCode);
        sb.append(", grayVersionCode=").append(grayVersionCode);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", resourceUrl=").append(resourceUrl);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", updateInfo=").append(updateInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
