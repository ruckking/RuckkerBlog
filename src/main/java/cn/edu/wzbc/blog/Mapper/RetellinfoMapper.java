package cn.edu.wzbc.blog.Mapper;

import cn.edu.wzbc.blog.Entity.Retellinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//映射
@Repository
public interface RetellinfoMapper {
    //xwd工作部分
    //注解
    /*
    insert，返回值是：新插入行的主键（primary key）。
    update/delete，返回值是：更新或删除的行数。
    */

    @Select("select * from retellinfo ")
    List<Retellinfo> getRetellList();//获得所有留言列表

    @Select("select * from retellinfo where blogid = #{blogid}")
    List<Retellinfo> getRetellListByBlogId(@Param("blogid") long blogid);//获得指定博客id的留言列表

    Retellinfo getOneRetellById(@Param("id") long id); //根据留言id查询出一条留言

    @Update("update retellinfo set retellpasser = #{retellpasser}, retelldate = #{retelldate} ,blogid= #{blogid}, retellcontent= #{retellcontent} where retellid = #{retellid}")
    int updateRetell(Retellinfo Retellinfo);//修改留言,这条不用你写.

    @Delete("delete from retellinfo where retellid = #{id}")
    int deleteRetell(@Param("id") long id);//根据id删除留言

    @Insert("insert into retellinfo (retellpasser,retellcontent,retelldate,blogid) " +
            "values (#{retellpasser},#{retellcontent},#{retelldate},#{blogid})")
    int addRetell(Retellinfo Retellinfo);//新增留言，这条不用你写.

    @Select("select * from retellinfo  where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(retelldate)")
    List<Retellinfo> getRecentlyRetellList();

    @Select("select count(retellid) from retellinfo")
    int getRetellCount();
}

