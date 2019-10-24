package cn.edu.wzbc.blog.Service;

import cn.edu.wzbc.blog.Entity.Retellinfo;
import cn.edu.wzbc.blog.Mapper.RetellinfoMapper;
import cn.edu.wzbc.blog.Util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "c2")
public class RetellinfoService {
    @Autowired
    RetellinfoMapper retellinfoMapper;

    @Cacheable
    public CommonReturnType getRecentlyRetellList() {
        int result = retellinfoMapper.getRecentlyRetellList().size();
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }

    }

    @Cacheable
    public CommonReturnType getRetellList() {
        List<Retellinfo> result = retellinfoMapper.getRetellList();
        if (result != null) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    @Cacheable
    public CommonReturnType getRetellByBlogid(long blogid) {
        List<Retellinfo> result = retellinfoMapper.getRetellListByBlogId(blogid);
        if (result != null) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }

    public CommonReturnType saveOrUpdateRetell(Retellinfo retellinfo) {
        if (retellinfo.getRetellid() != 0) {
            retellinfo.setRetellid(retellinfo.getRetellid());
            int result = retellinfoMapper.updateRetell(retellinfo);
            if (result != 0) {
                return CommonReturnType.create(result);
            } else {
                return CommonReturnType.create(null, "fail");
            }
        } else {
            int result = retellinfoMapper.addRetell(retellinfo);
            if (result != 0) {
                return CommonReturnType.create(result);
            } else {
                return CommonReturnType.create(null, "fail");
            }
        }
    }

    public int getRetllCount() {
        return retellinfoMapper.getRetellCount();
    }

    public CommonReturnType deleteRetell(long retellid) {
        int result = retellinfoMapper.deleteRetell(retellid);
        if (result != 0) {
            return CommonReturnType.create(result);
        } else {
            return CommonReturnType.create(null, "fail");
        }
    }
}
