package kybmig.ssm.model;

import java.util.ArrayList;

public class TopicModel {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String createdTime;
    private String updatedTime;
    private String topicId;
    private String username;
    private ArrayList<TopicCommentModel> commentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public ArrayList<TopicCommentModel> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<TopicCommentModel> commentList) {
        this.commentList = commentList;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topidId) {
        this.topicId = topidId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

