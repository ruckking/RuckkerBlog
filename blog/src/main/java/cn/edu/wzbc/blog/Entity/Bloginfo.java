package cn.edu.wzbc.blog.Entity;

import java.io.Serializable;

public class Bloginfo implements Serializable {
    private Long blogid;
    private String blogcontent;
    private String blogdate;
    private String blogpasser;
    private String blogtitle;
    private Boolean enabled;
    private int star;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bloginfo{" +
                "blogid=" + blogid +
                ", blogcontent='" + blogcontent + '\'' +
                ", blogdate='" + blogdate + '\'' +
                ", blogpasser='" + blogpasser + '\'' +
                ", blogtitle='" + blogtitle + '\'' +
                ", enabled=" + enabled +
                ", star=" + star +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getBlogid() {
        return blogid;
    }

    public void setBlogid(Long blogid) {
        this.blogid = blogid;
    }

    public String getBlogcontent() {
        return blogcontent;
    }

    public void setBlogcontent(String blogcontent) {
        this.blogcontent = blogcontent;
    }

    public String getBlogdate() {
        return blogdate;
    }

    public void setBlogdate(String blogdate) {
        this.blogdate = blogdate;
    }

    public String getBlogpasser() {
        return blogpasser;
    }

    public void setBlogpasser(String blogpasser) {
        this.blogpasser = blogpasser;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle;
    }
}
