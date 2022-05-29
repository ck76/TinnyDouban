package com.ck.tinnydouban.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private Long id;

    private Long doubanId;

    private String name;

    private String alias;

    private String coverUrl;

    private Double doubanScore;

    private Integer doubanVote;

    private Integer mins;

    private Date releaseDate;

    private Integer year;

    private Date createdTime;

    private Date updatedTime;

    private String storyline;

    private String tags;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(Long doubanId) {
        this.doubanId = doubanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public Double getDoubanScore() {
        return doubanScore;
    }

    public void setDoubanScore(Double doubanScore) {
        this.doubanScore = doubanScore;
    }

    public Integer getDoubanVote() {
        return doubanVote;
    }

    public void setDoubanVote(Integer doubanVote) {
        this.doubanVote = doubanVote;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline == null ? null : storyline.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", doubanId=").append(doubanId);
        sb.append(", name=").append(name);
        sb.append(", alias=").append(alias);
        sb.append(", coverUrl=").append(coverUrl);
        sb.append(", doubanScore=").append(doubanScore);
        sb.append(", doubanVote=").append(doubanVote);
        sb.append(", mins=").append(mins);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", year=").append(year);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", storyline=").append(storyline);
        sb.append(", tags=").append(tags);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
