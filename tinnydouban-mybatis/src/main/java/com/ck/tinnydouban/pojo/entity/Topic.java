package com.ck.tinnydouban.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {
    private Long id;

    private String name;

    private Integer topicType;

    private Long movieId;

    private Long createUserId;

    private Long postNum;

    private Long userNum;

    private Date createdTime;

    private Date updatedTime;

    private String topicAbstract;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getPostNum() {
        return postNum;
    }

    public void setPostNum(Long postNum) {
        this.postNum = postNum;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
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

    public String getTopicAbstract() {
        return topicAbstract;
    }

    public void setTopicAbstract(String topicAbstract) {
        this.topicAbstract = topicAbstract == null ? null : topicAbstract.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", topicType=").append(topicType);
        sb.append(", movieId=").append(movieId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", postNum=").append(postNum);
        sb.append(", userNum=").append(userNum);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", topicAbstract=").append(topicAbstract);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
