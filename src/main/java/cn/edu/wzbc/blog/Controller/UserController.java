package cn.edu.wzbc.blog.Controller;

import cn.edu.wzbc.blog.Entity.User;
import cn.edu.wzbc.blog.Service.UserService;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")

public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/Login")
    public CommonReturnType Login(@RequestParam String username, @RequestParam String password) {
        return userService.CheckLogin(username, password);
    }


    @RequestMapping("/Register")
    public CommonReturnType Register(@RequestParam String username, @RequestParam String password, @RequestParam String telephone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("user");
        user.setEnabled(true);
        user.setTelephone(telephone);
        return userService.doRegister(user);
    }

    @GetMapping("/getUserListExpectAdmin")
    public CommonReturnType getUserListExpectAdmin() {
        return userService.getUserExceptAdmin();
    }

    @RequestMapping("/GetUserList")
    public List<User> getUserList() {
        return userService.getAllUser();
    }


    @RequestMapping("/user")
    public String user() {
        return "user角色访问";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin角色访问";
    }

    @PostMapping("/saveOrUpdateUser")
    public CommonReturnType saveOrUpdateUser(@RequestParam String username,
                                             @RequestParam String password,
                                             @RequestParam String telephone,
                                             @RequestParam String role,
                                             @RequestParam Boolean enabled,
                                             @RequestParam long userid) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRole(role);
        user.setEnabled(enabled);
        user.setUserid(userid);
        return userService.saveorUpdateUser(user);
    }

    @DeleteMapping("/deleteUserById")
    public CommonReturnType deleteUserById(@RequestParam long userid) {
        return userService.deleteUserById(userid);
    }


}



