package cn.edu.wzbc.blog.Entity;

import java.io.Serializable;

public class Retellinfo implements Serializable {
    private Long retellid;
    private String retellpasser;
    private String retellcontent;
    private String retelldate;
    private Long blogid;

    public Long getRetellid() {
        return retellid;
    }

    public void setRetellid(Long retellid) {
        this.retellid = retellid;
    }

    public String getRetellpasser() {
        return retellpasser;
    }

    public void setRetellpasser(String retellpasser) {
        this.retellpasser = retellpasser;
    }

    public String getRetellcontent() {
        return retellcontent;
    }

    public void setRetellcontent(String retellcontent) {
        this.retellcontent = retellcontent;
    }

    public String getRetelldate() {
        return retelldate;
    }

    public void setRetelldate(String retelldate) {
        this.retelldate = retelldate;
    }

    public Long getBlogid() {
        return blogid;
    }

    public void setBlogid(Long blogid) {
        this.blogid = blogid;
    }
}
