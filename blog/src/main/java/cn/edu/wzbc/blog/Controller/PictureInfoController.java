package cn.edu.wzbc.blog.Controller;

import cn.edu.wzbc.blog.Entity.Pictureinfo;
import cn.edu.wzbc.blog.Service.PictureinfoService;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class PictureInfoController {
    final String Ruck = "ruck";
    final String Path = "D:\\VueBlog2\\src\\assets\\img\\";
    @Autowired
    PictureinfoService pictureinfoService;

    @PostMapping("/uploadImg")
    public CommonReturnType uploadImg(@RequestParam("file") MultipartFile file) {
        CommonReturnType result = null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String houzhui = fileName.substring((fileName.length() - 3)).toUpperCase();
            fileName = fileName.substring(0, fileName.length() - 3);
            fileName = fileName + houzhui;
            System.out.println(fileName);
            String path = null;
            String trueFileName = Ruck + "_" + fileName;
            // 设置存放图片文件的路径
            path = Path + trueFileName;
            File dest = new File(path);
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);
                Pictureinfo pictureinfo = new Pictureinfo();
                pictureinfo.setPhotourl(path);
                result = pictureinfoService.AddPicture(pictureinfo);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return CommonReturnType.create("102", "fail");
            }
        } else {
            return CommonReturnType.create("103", "fail");
        }
    }

    @PostMapping("/addPhotoInfo")
    public CommonReturnType addPhoto(@RequestParam long photoid, @RequestParam String phototime, @RequestParam String photoabout) {
        Pictureinfo pictureinfo = new Pictureinfo();
        pictureinfo.setPhotoid(photoid);
        pictureinfo.setPhototime(phototime);
        pictureinfo.setPhotoabout(photoabout);
        return pictureinfoService.AddPhotoinfo(pictureinfo);
    }

    @GetMapping("/getPhotoList")
    public CommonReturnType getPohtoList() {
        return pictureinfoService.getPhotoList();
    }

    @GetMapping("/getAllPohtoList")
    public CommonReturnType getAllPohtoList() {
        return pictureinfoService.getAllPhotoList();
    }

    @GetMapping("/getPhotoByBlogId")
    public CommonReturnType getPhotoByBlogId(@RequestParam String blogid) {
        return pictureinfoService.getPhotoByBlogId(blogid);
    }

    @DeleteMapping("/deletePhotoById")
    public CommonReturnType deletePhotoById(@RequestParam long id) {
        Pictureinfo result = (Pictureinfo) pictureinfoService.deletelPhotoById(id).getData();
        if (result != null) {
            String Path = result.getPhotourl();
            File file = new File(Path);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    System.out.println("删除单个文件" + Path + "成功！");
                    return CommonReturnType.create(result);
                }
            }
        } else {
            return CommonReturnType.create(null, "fail");
        }
        return CommonReturnType.create(null, "fail");
    }

    @GetMapping("/getCarouselPic")
    public CommonReturnType getCarouselPic() {
        return pictureinfoService.getCarouselPicture();
    }
}

