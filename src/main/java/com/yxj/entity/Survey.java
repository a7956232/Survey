package com.yxj.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 95 on 2016/11/22.
 */
public class Survey extends BaseEntity{

    private static final long serialVersionUID = -4471639268654126328L;
    private Integer id;
    private String title = "未命名";
    private Date createTime = new Date();
    //状态是否关闭
    boolean closed;
    //logo路径
    private String logoPhotoPath;
    //最小页序
    private float minOrderno;
    //最大页序
    private float maxOrderno;

    //survey到user多对一
    private User user;

    //survey到Page一对多
    private Set<Page> pages = new HashSet<>();

    public float getMinOrderno() {
        return minOrderno;
    }

    public void setMinOrderno(float minOrderno) {
        this.minOrderno = minOrderno;
    }

    public float getMaxOrderno() {
        return maxOrderno;
    }

    public void setMaxOrderno(float maxOrderno) {
        this.maxOrderno = maxOrderno;
    }

    public String getLogoPhotoPath() {
        return logoPhotoPath;
    }

    public void setLogoPhotoPath(String logoPhotoPath) {
        this.logoPhotoPath = logoPhotoPath;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
