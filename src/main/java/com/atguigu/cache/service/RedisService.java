package com.atguigu.cache.service;

import com.atguigu.cache.utils.RedisUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ruanjiayu
 * @dateTime 2019/6/21 9:58
 */
@Service
@CacheConfig(cacheNames = "redis")
public class RedisService {

    @Resource
    private RedisUtil redisUtil;

    /** -------------------通过key来查询数据的时候，需要注意如果key的值为字符串，那么查询需要使用使用转义符--------------------- */
    /**
     * 删除key
     * @param key
     */
    public void delete(String key){
        redisUtil.delete(key);
    }

    /**
     * 批量查询key
     * @param keys
     */
    public void delete(Collection<String> keys){
        redisUtil.delete(keys);
    }

    /**
     * 判断是否存在某个key
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
        return redisUtil.hasKey(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @param unit 单位
     */
    public void expire(String key, long timeout, TimeUnit unit){
        redisUtil.expire(key,timeout,unit);
    }
    /** -------------------要进行模糊查询，需要先进行key的遍历以便来获取数据--------------------- */

    /**
     * 先通过模糊查询来得到对应keys
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
       return redisUtil.keys(pattern);
    }


    /** -------------------string相关操作--------------------- */
    /**
     * 设置redis内存储的内容
     * @param key
     * @param value
     */
    public void stringSet(String key, String value){
        redisUtil.set(key,value);
    }

    /**
     * 设置redis内存储的内容
     * @param key
     * @param value
     */
    @Cacheable(key = "#key")
    public String stringCache(String key, String value){
        return value;
    }

    /**
     * 得到key对应的值，不会讲redis内的值弹出
     * @param key
     */
    public String stringGet(String key){
      return  redisUtil.get(key);
    }

    /**
     * 传入序列keys来获取序列value
     * @param keys
     * @return
     */
    public List<String> multiGet(Collection<String> keys){
        return redisUtil.multiGet(keys);
    }

    /**
     * 来获取value的值的长度
     * @param key
     * @return
     */
    public Long size(String key){
        return redisUtil.size(key);
    }





}
