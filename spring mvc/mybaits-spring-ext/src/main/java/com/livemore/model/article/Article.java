package com.livemore.model.article;

import java.util.Date;

import com.livemore.model.BaseModel;

public class Article  extends BaseModel{
    private Long id;

    private String title;

    private Integer categoryId;

    private String categoryName;

    private Long createrUser;

    private Date createrTime;

    private String remark;

    private Date lastIndexTime;

    private String htmlContent;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Long getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(Long createrUser) {
        this.createrUser = createrUser;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLastIndexTime() {
        return lastIndexTime;
    }

    public void setLastIndexTime(Date lastIndexTime) {
        this.lastIndexTime = lastIndexTime;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }
}