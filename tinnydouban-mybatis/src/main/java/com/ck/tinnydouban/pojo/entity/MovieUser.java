package com.ck.tinnydouban.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class MovieUser implements Serializable {
    private Long id;

    private Long userId;

    private Long movieId;

    private Long movieDoubanId;

    private Boolean wannaSee;

    private Boolean haveSeen;

    private Double score;

    private Date wannaSeeTime;

    private Date haveSeenTime;

    private Date scoreTime;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieDoubanId() {
        return movieDoubanId;
    }

    public void setMovieDoubanId(Long movieDoubanId) {
        this.movieDoubanId = movieDoubanId;
    }

    public Boolean getWannaSee() {
        return wannaSee;
    }

    public void setWannaSee(Boolean wannaSee) {
        this.wannaSee = wannaSee;
    }

    public Boolean getHaveSeen() {
        return haveSeen;
    }

    public void setHaveSeen(Boolean haveSeen) {
        this.haveSeen = haveSeen;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getWannaSeeTime() {
        return wannaSeeTime;
    }

    public void setWannaSeeTime(Date wannaSeeTime) {
        this.wannaSeeTime = wannaSeeTime;
    }

    public Date getHaveSeenTime() {
        return haveSeenTime;
    }

    public void setHaveSeenTime(Date haveSeenTime) {
        this.haveSeenTime = haveSeenTime;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", movieId=").append(movieId);
        sb.append(", movieDoubanId=").append(movieDoubanId);
        sb.append(", wannaSee=").append(wannaSee);
        sb.append(", haveSeen=").append(haveSeen);
        sb.append(", score=").append(score);
        sb.append(", wannaSeeTime=").append(wannaSeeTime);
        sb.append(", haveSeenTime=").append(haveSeenTime);
        sb.append(", scoreTime=").append(scoreTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
