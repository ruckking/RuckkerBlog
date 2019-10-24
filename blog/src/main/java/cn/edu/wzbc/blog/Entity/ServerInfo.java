package cn.edu.wzbc.blog.Entity;

import java.io.Serializable;

public class ServerInfo implements Serializable {
    private Integer IndexHoverNumber;
    private Integer BlogHoverNumber;
    private Integer PhotoHoverNumber;
    private Integer AboutHoverNumber;
    private Integer RetellNumber;

    public Integer getRetellNumber() {
        return RetellNumber;
    }

    public void setRetellNumber(Integer retellNumber) {
        RetellNumber = retellNumber;
    }

    public Integer getPhotoHoverNumber() {
        return PhotoHoverNumber;
    }

    public void setPhotoHoverNumber(Integer photoHoverNumber) {
        PhotoHoverNumber = photoHoverNumber;
    }

    public Integer getAboutHoverNumber() {
        return AboutHoverNumber;
    }

    public void setAboutHoverNumber(Integer aboutHoverNumber) {
        AboutHoverNumber = aboutHoverNumber;
    }

    public Integer getIndexHoverNumber() {
        return IndexHoverNumber;
    }

    public void setIndexHoverNumber(Integer indexHoverNumber) {
        IndexHoverNumber = indexHoverNumber;
    }

    public Integer getBlogHoverNumber() {
        return BlogHoverNumber;
    }

    public void setBlogHoverNumber(Integer blogHoverNumer) {
        BlogHoverNumber = blogHoverNumer;
    }
}
