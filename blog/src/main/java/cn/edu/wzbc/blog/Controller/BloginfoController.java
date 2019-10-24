package cn.edu.wzbc.blog.Controller;

import cn.edu.wzbc.blog.Entity.Bloginfo;
import cn.edu.wzbc.blog.Service.BloginfoService;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BloginfoController {
    @Autowired
    BloginfoService bloginfoService;

    @RequestMapping(value = "/getBlogList")
    public CommonReturnType getBlogList() {
        return bloginfoService.getBlogList();
    }

    @RequestMapping(value = "/getBlogListEnabled")
    public CommonReturnType getBlogListEnabled() {
        return bloginfoService.getBlogListEnabled();
    }

    @PostMapping(value = "/updateBlog")
    public CommonReturnType updateBlog(@RequestParam("blogid") long blogId,
                                       @RequestParam("blogcontent") String blogcontent,
                                       @RequestParam("blogdate") String blogdate,
                                       @RequestParam("blogtitle") String blogtitle,
                                       @RequestParam("enabled") Boolean enabled,
                                       @RequestParam("type") String type,
                                       @RequestParam("star") Integer star,
                                       @RequestParam("blogpasser") String blogpasser) {
        Bloginfo bloginfo = new Bloginfo();
        bloginfo.setBlogcontent(blogcontent);
        bloginfo.setBlogdate(blogdate);
        bloginfo.setBlogid(blogId);
        bloginfo.setBlogtitle(blogtitle);
        bloginfo.setEnabled(enabled);
        bloginfo.setType(type);
        bloginfo.setBlogpasser(blogpasser);
        bloginfo.setStar(star);
        return bloginfoService.saveOrupdateBlog(bloginfo);
    }

    @DeleteMapping(value = "/deleteBlog")
    public CommonReturnType deleteBlog(@RequestParam("blogid") long blogId) {
        return bloginfoService.deleteBlog(blogId);

    }

    @GetMapping(value = "/getBlogById")
    public CommonReturnType getBlogById(@RequestParam("blogid") long blogId) {
        return bloginfoService.getBlogById(blogId);
    }

    @GetMapping(value = "/getRecentlyBlog")
    public CommonReturnType getRecentlyBlog() {
        return bloginfoService.getRecentlyBlogList();
    }

    @GetMapping(value = "/getBlogStar")
    public CommonReturnType getBlogStar() {
        return bloginfoService.getBlogStar();
    }

    @GetMapping(value = "/getTopBlog")
    public CommonReturnType getTopBlog() {
        return bloginfoService.getTopBlog();
    }

    @PostMapping(value = "/addStar")
    public CommonReturnType addStar(@RequestParam long blogid) {
        Bloginfo bloginfo = (Bloginfo) bloginfoService.getBlogById(blogid).getData();
        bloginfo.setStar(bloginfo.getStar() + 1);
        return bloginfoService.AddStar(bloginfo);
    }

    @GetMapping(value = "/getPopType")
    public CommonReturnType getPopType() {
        return bloginfoService.getPopType();
    }

    @GetMapping(value = "/getBlogListByType")
    public CommonReturnType getBlogListByType(@RequestParam String type) {
        return bloginfoService.getBlogListByType(type);
    }
}
