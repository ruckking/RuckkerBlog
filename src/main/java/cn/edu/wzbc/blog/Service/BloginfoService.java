package cn.edu.wzbc.blog.Service;

import cn.edu.wzbc.blog.Entity.Bloginfo;
import cn.edu.wzbc.blog.Mapper.BloginfoMapper;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "c1")
public class BloginfoService {
    @Autowired
    BloginfoMapper bloginfoMapper;

    public List<Bloginfo> getBlogList(String keyword) {
        return bloginfoMapper.getBlogList(keyword);
    }

    @Cacheable
    public CommonReturnType getBlogList() {
        List<Bloginfo> result = bloginfoMapper.getBlogList2();
        return CommonReturnType.create(result);
    }

    @Cacheable
    public CommonReturnType getBlogListEnabled() {
        List<Bloginfo> result = bloginfoMapper.getBlogListEnabled();
        return CommonReturnType.create(result);
    }

    @CacheEvict(cacheNames = "c1")
    public CommonReturnType saveOrupdateBlog(Bloginfo bloginfo) {
        if (bloginfo.getBlogid() != 0) {
            int result = bloginfoMapper.updateBlog(bloginfo);
            if (result != 0) {
                return CommonReturnType.create(result);
            } else {
                return CommonReturnType.create(result, "fail");
            }
        } else {
            int result = bloginfoMapper.addBlog(bloginfo);
            if (result != 0) {
                return CommonReturnType.create(result);
            } else {
                return CommonReturnType.create(result, "fail");
            }
        }
    }

    public CommonReturnType deleteBlog(long blogId) {
        int result = bloginfoMapper.deleteBlog(blogId);
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(result, "fail");
        }
    }

    public CommonReturnType getBlogById(long blogId) {
        Bloginfo bloginfo = new Bloginfo();
        bloginfo = bloginfoMapper.getOneBlogById(blogId);
        if (bloginfo != null) {
            return CommonReturnType.create(bloginfo);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    @Cacheable
    public CommonReturnType getRecentlyBlogList() {
        int result = bloginfoMapper.getRecentlyBlogList().size();
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }

    }

    @Cacheable
    public CommonReturnType getBlogStar() {
        int result = bloginfoMapper.getBlogStar();
        return CommonReturnType.create(result);
    }

    @Cacheable
    public CommonReturnType getTopBlog() {
        List<Bloginfo> result = bloginfoMapper.getTopBlogList();
        if (result != null) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    public CommonReturnType AddStar(Bloginfo bloginfo) {
        int result = bloginfoMapper.AddStar(bloginfo);
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    public CommonReturnType getPopType() {
        List<Bloginfo> result = bloginfoMapper.getPopType();
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    public CommonReturnType getBlogListByType(String type) {
        List<Bloginfo> result = bloginfoMapper.getBlogListByType(type);
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

}
