package com.ck.tinnydouban.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class FilmmakerProfession implements Serializable {
    private Long id;

    private Long filmmakerId;

    private Long filmmakerDoubanId;

    private Long professionId;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilmmakerId() {
        return filmmakerId;
    }

    public void setFilmmakerId(Long filmmakerId) {
        this.filmmakerId = filmmakerId;
    }

    public Long getFilmmakerDoubanId() {
        return filmmakerDoubanId;
    }

    public void setFilmmakerDoubanId(Long filmmakerDoubanId) {
        this.filmmakerDoubanId = filmmakerDoubanId;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
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
        sb.append(", filmmakerId=").append(filmmakerId);
        sb.append(", filmmakerDoubanId=").append(filmmakerDoubanId);
        sb.append(", professionId=").append(professionId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
