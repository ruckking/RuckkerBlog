package cn.edu.wzbc.blog.Mapper;

import cn.edu.wzbc.blog.Entity.Bloginfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//映射
@Repository
public interface BloginfoMapper {

    @Select(" select * from bloginfo WHERE blogtitle like '%#{keyword}%' ")
    List<Bloginfo> getBlogList(@Param("keyword") String keyword);//根据关键字查询

    @Select("select * from bloginfo")
    List<Bloginfo> getBlogList2();//没有关键字查询

    @Select("select * from bloginfo where enabled = 1")
    List<Bloginfo> getBlogListEnabled();//没有关键字查询

    @Select("select sum(star) from bloginfo")
    int getBlogStar();

    //xwd工作部分
    //注解
    /*
    insert，返回值是：新插入行的主键（primary key）。
    update/delete，返回值是：更新或删除的行数。
    */


    @Select("select * from bloginfo where type = #{type}")
//根据博客类型查询
    List<Bloginfo> getBlogListByType(@Param("type") String type);

    @Select("select * from bloginfo where blogid = #{id}")
    Bloginfo getOneBlogById(@Param("id") Long id); //根据博客id查询出一条博客

    @Update("update bloginfo set blogcontent= #{blogcontent}, " +
            "blogpasser=#{blogpasser}," +
            "blogdate=#{blogdate}," +
            "blogtitle=#{blogtitle}," +
            "enabled=#{enabled}," +
            "star=#{star}," +
            "type=#{type}" +
            " where blogid = #{blogid}")
    int updateBlog(Bloginfo bloginfo);//修改博客,这条不用你写.

    @Delete("delete from bloginfo where blogid = #{id}")
    int deleteBlog(@Param("id") Long id);//根据id删除博客

    @Insert("insert into bloginfo (blogcontent,blogpasser,blogdate,blogtitle,enabled,star,type) value " +
            "(#{blogcontent},#{blogpasser},#{blogdate},#{blogtitle},#{enabled},#{star},#{type})")
    int addBlog(Bloginfo bloginfo);//新增博客，这条不用你写.

    @Select("select * from bloginfo  where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(blogdate)")
    List<Bloginfo> getRecentlyBlogList();

    @Select("select * from bloginfo order by star desc limit 5")
    List<Bloginfo> getTopBlogList();

    @Update("update bloginfo set star = #{star} where blogid = #{blogid}")
    int AddStar(Bloginfo bloginfo);

    @Select("SELECT DISTINCT type,count( * ) AS count FROM bloginfo GROUP BY type ORDER BY count DESC limit 15")
    List<Bloginfo> getPopType();


}

