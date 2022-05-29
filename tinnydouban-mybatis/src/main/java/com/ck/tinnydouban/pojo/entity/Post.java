package com.ck.tinnydouban.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private Long id;

    private String title;

    private Long topicId;

    private Boolean postType;

    private Long movieId;

    private Long createUserId;

    private Long likeNum;

    private Long commentNum;

    private Date lastCommentTime;

    private Date createdTime;

    private Date updatedTime;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Boolean getPostType() {
        return postType;
    }

    public void setPostType(Boolean postType) {
        this.postType = postType;
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

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", topicId=").append(topicId);
        sb.append(", postType=").append(postType);
        sb.append(", movieId=").append(movieId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", lastCommentTime=").append(lastCommentTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
