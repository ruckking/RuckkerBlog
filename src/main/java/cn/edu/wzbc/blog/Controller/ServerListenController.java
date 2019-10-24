package cn.edu.wzbc.blog.Controller;

import cn.edu.wzbc.blog.Service.ServerInfoService;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerListenController {
    @Autowired
    ServerInfoService serverInfoService;

    @GetMapping("/getServerInfo")
    public CommonReturnType getServerInfo() {
        return serverInfoService.getServerInfo();
    }

    @PostMapping("/addIndexHover")
    public void addIndexHover() {
        serverInfoService.AddIndexHoverNum();
    }

    @PostMapping("/addPhotoHover")
    public void addPhotoHover() {
        serverInfoService.AddPhotoHoverNumber();
    }

    @PostMapping("/addAboutHover")
    public void addAboutHover() {
        serverInfoService.AddAboutHoverNumber();
    }

    @PostMapping("/addBlogHover")
    public void addBlogHover() {
        serverInfoService.AddBlogHoverNumber();
    }

    @PostMapping("/addRetellNum")
    public void addRetellNum() {
        serverInfoService.AddRetellNumber();
    }
}
