package cn.edu.wzbc.blog.Mapper;

import cn.edu.wzbc.blog.Entity.Pictureinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//映射
@Repository
public interface PictureinfoMapper {

    @Select("select * from picture where photoabout not like '%首页%' and photoabout not like '%轮播%'")
    List<Pictureinfo> getPictureList();

    @Select("select * from picture order by phototime    desc")
    List<Pictureinfo> getPictureListAll();

    //
    @Insert("insert into picture (photourl) values(#{photourl})")
    @Options(useGeneratedKeys = true, keyProperty = "photoid", keyColumn = "photoid")
    int addPicture(Pictureinfo pictureinfo);

    @Update("update picture set phototime=#{phototime},photoabout=#{photoabout} where photoid=#{photoid} ")
    int addPhotoinfo(Pictureinfo pictureinfo);

    @Select("select * from picture where photoabout= #{blogid}")
    List<Pictureinfo> getPictureByBlogId(String blogid);

    @Select("select * from picture where photoid= #{id}")
    Pictureinfo getPictureById(long id);

    @Delete("delete from picture where photoid = #{id}")
    int deletePictureById(long id);

    @Select("select * from picture where photoabout  like '%轮播%'")
    List<Pictureinfo> getcarouselPicture();
}
