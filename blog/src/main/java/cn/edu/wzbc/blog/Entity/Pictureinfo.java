package cn.edu.wzbc.blog.Entity;

import java.io.Serializable;

public class Pictureinfo implements Serializable {
    private Long photoid;
    private String phototime;
    private String photoabout;
    private String photourl;

    public Long getPhotoid() {
        return photoid;
    }

    public void setPhotoid(Long photoid) {
        this.photoid = photoid;
    }

    public String getPhototime() {
        return phototime;
    }

    public void setPhototime(String phototime) {
        this.phototime = phototime;
    }

    public String getPhotoabout() {
        return photoabout;
    }

    public void setPhotoabout(String photoabout) {
        this.photoabout = photoabout;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
