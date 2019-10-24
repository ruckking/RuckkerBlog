package cn.edu.wzbc.blog.Service;


import cn.edu.wzbc.blog.Entity.Pictureinfo;
import cn.edu.wzbc.blog.Mapper.PictureinfoMapper;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "c5")
public class PictureinfoService {
    @Autowired
    PictureinfoMapper pictureinfoMapper;

    @Cacheable
    public CommonReturnType getPictureinfoList() {
        return CommonReturnType.create(pictureinfoMapper.getPictureList());
    }

    public CommonReturnType AddPicture(Pictureinfo pictureinfo) {
        pictureinfoMapper.addPicture(pictureinfo);
        Long result = pictureinfo.getPhotoid();
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(result, "fail");
        }

    }

    public CommonReturnType AddPhotoinfo(Pictureinfo pictureinfo) {
        int result = pictureinfoMapper.addPhotoinfo(pictureinfo);
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(result, "fail");
        }
    }

    @Cacheable
    public CommonReturnType getPhotoList() {
        List<Pictureinfo> result = pictureinfoMapper.getPictureList();
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    @Cacheable
    public CommonReturnType getAllPhotoList() {
        List<Pictureinfo> result = pictureinfoMapper.getPictureListAll();
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    @Cacheable
    public CommonReturnType getPhotoByBlogId(String blogid) {
        List<Pictureinfo> result = pictureinfoMapper.getPictureByBlogId(blogid);
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    public CommonReturnType deletelPhotoById(long id) {
        Pictureinfo result = pictureinfoMapper.getPictureById(id);
        int result2 = pictureinfoMapper.deletePictureById(id);
        if (result2 != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create("509", "fail");
        }
    }

    @Cacheable
    public CommonReturnType getCarouselPicture() {
        List<Pictureinfo> result = pictureinfoMapper.getcarouselPicture();
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }
}
