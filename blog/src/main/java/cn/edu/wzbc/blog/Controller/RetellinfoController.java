package cn.edu.wzbc.blog.Controller;

import cn.edu.wzbc.blog.Entity.Retellinfo;
import cn.edu.wzbc.blog.Service.RetellinfoService;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RetellinfoController {
    @Autowired
    RetellinfoService retellinfoService;

    @GetMapping("/getRecentlyRetell")
    public CommonReturnType getRecentlyRetell() {
        return retellinfoService.getRecentlyRetellList();
    }

    @GetMapping("/getRetellList")
    public CommonReturnType getRetellList() {
        return retellinfoService.getRetellList();
    }

    @GetMapping("/getRetellByBlogId")
    public CommonReturnType getRetellByBlogId(@RequestParam long blogid) {
        return retellinfoService.getRetellByBlogid(blogid);
    }

    @PostMapping("/addRetell")
    public CommonReturnType addRetell(@RequestParam String retellpasser, @RequestParam String retellcontent, @RequestParam String retelldate, @RequestParam long blogid, @RequestParam long retellid) {
        Retellinfo retellinfo = new Retellinfo();
        retellinfo.setRetellpasser(retellpasser);
        retellinfo.setRetellcontent(retellcontent);
        retellinfo.setRetelldate(retelldate);
        retellinfo.setBlogid(blogid);
        retellinfo.setRetellid(retellid);
        return retellinfoService.saveOrUpdateRetell(retellinfo);

    }

    @DeleteMapping("/deleteRetell")
    public CommonReturnType deleteRetell(@RequestParam long retellid) {
        return retellinfoService.deleteRetell(retellid);
    }
}




