package cn.edu.wzbc.blog.Service;

import cn.edu.wzbc.blog.Entity.ServerInfo;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "c3")
public class ServerInfoService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RetellinfoService retellinfoService;

    public void InitServerInfo() {
        redisTemplate.boundValueOps("Indexhovernum").set(0);
        redisTemplate.boundValueOps("BlogHoverNumber").set(0);
        redisTemplate.boundValueOps("PhotoHoverNumber").set(0);
        redisTemplate.boundValueOps("AboutHoverNumber").set(0);
        redisTemplate.boundValueOps("RetellNumber").set(retellinfoService.getRetllCount());
    }

    public CommonReturnType getServerInfo() {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setIndexHoverNumber((Integer) redisTemplate.boundValueOps("Indexhovernum").get());
        serverInfo.setBlogHoverNumber((Integer) redisTemplate.boundValueOps("BlogHoverNumber").get());
        serverInfo.setAboutHoverNumber((Integer) redisTemplate.boundValueOps("AboutHoverNumber").get());
        serverInfo.setPhotoHoverNumber((Integer) redisTemplate.boundValueOps("PhotoHoverNumber").get());
        serverInfo.setRetellNumber((Integer) redisTemplate.boundValueOps("RetellNumber").get());
        return CommonReturnType.create(serverInfo);
    }

    public void AddIndexHoverNum() {
        redisTemplate.boundValueOps("Indexhovernum").set((Integer) redisTemplate.boundValueOps("Indexhovernum").get() + 1);
    }

    public void AddBlogHoverNumber() {
        redisTemplate.boundValueOps("BlogHoverNumber").set((Integer) redisTemplate.boundValueOps("BlogHoverNumber").get() + 1);
    }

    public void AddPhotoHoverNumber() {
        redisTemplate.boundValueOps("PhotoHoverNumber").set((Integer) redisTemplate.boundValueOps("PhotoHoverNumber").get() + 1);
    }

    public void AddAboutHoverNumber() {
        redisTemplate.boundValueOps("AboutHoverNumber").set((Integer) redisTemplate.boundValueOps("AboutHoverNumber").get() + 1);
    }

    public void AddRetellNumber() {
        redisTemplate.boundValueOps("RetellNumber").set((Integer) redisTemplate.boundValueOps("RetellNumber").get() + 1);
    }

}
