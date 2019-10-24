package cn.edu.wzbc.blog.Service;

import cn.edu.wzbc.blog.Config.TokenProccessor;
import cn.edu.wzbc.blog.Entity.User;
import cn.edu.wzbc.blog.Mapper.UserMapper;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@CacheConfig(cacheNames = "c4")
public class UserService {
    @Autowired
    UserMapper userMapper;
    TokenProccessor tokenMaker = TokenProccessor.getInstance();

    public CommonReturnType CheckLogin(String username, String password) {
        User result = userMapper.CheckLogin(username, password);
        if (result != null) {
            result.setToken(tokenMaker.makeToken());
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "102");
        }
    }

    public CommonReturnType doRegister(User user) {
        int result = userMapper.doRegister(user);
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "102");
        }
    }

    public CommonReturnType saveorUpdateUser(User user) {
        if (user.getUserid() != 0) {
            int result = userMapper.UpdateUser(user);
            if (result != 0) {
                return CommonReturnType.create(result);
            } else {
                return CommonReturnType.create(null, "102");
            }
        } else {
            int result = userMapper.doRegister(user);
            if (result != 0) {
                return CommonReturnType.create(result);
            } else {
                return CommonReturnType.create(null, "102");
            }
        }

    }

    public CommonReturnType deleteUserById(long id) {
        int result = userMapper.deleteUserById(id);
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "102");
        }
    }

    @Cacheable
    public CommonReturnType getUserExceptAdmin() {
        List<User> result = userMapper.getUserExceptAdmin();
        if (!result.isEmpty()) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    @Cacheable
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Cacheable
    public List<User> getCUrrentUser(String username) {
        return userMapper.getOtherUser(username);
    }
}
