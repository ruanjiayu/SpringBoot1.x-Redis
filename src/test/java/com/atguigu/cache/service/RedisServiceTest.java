package com.atguigu.cache.service;

import com.atguigu.cache.Springboot01CacheApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author ruanjiayu
 * @dateTime 2019/6/21 10:02
 */
@Component
@Slf4j
public class RedisServiceTest extends Springboot01CacheApplicationTests{


    @Resource
    private RedisService redisService;


    @Test
    public void delete() throws Exception {
        redisService.delete("1*");
    }

    @Test
    public void hasKey() throws Exception {
        log.info(redisService.hasKey("111").toString());
    }

    @Test
    public void expire() throws Exception {
        redisService.expire("2",11, TimeUnit.MINUTES);
    }

    @Test
    public void stringSet() throws Exception {
        redisService.stringSet("33","43");
    }

    @Test
    public void stringCache() throws Exception {
        redisService.stringCache("33","44");
    }

    @Test
    public void stringGet() throws Exception {
        String key = String.format("%s", "2");
        log.info("【获得key对应的值】:{}",redisService.stringGet(key));
    }

    @Test
    public void multiGet() throws Exception {
        // 先进行模糊查询得到keys的值，在通过keys来获取value
       Set<String> keys = redisService.keys("redis:\"22\"");
       log.info("【模糊进行查询得到的数量为{}】:{}",redisService.multiGet(keys).size(),redisService.multiGet(keys));
    }

    @Test
    public void size() throws Exception {
        Long size = redisService.size("1");
        log.info("【获得value的长度】:{}",size);
    }




}