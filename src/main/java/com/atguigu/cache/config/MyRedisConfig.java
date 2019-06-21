package com.atguigu.cache.config;


import com.alibaba.fastjson.parser.ParserConfig;
import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class MyRedisConfig {

    /***
     *  1.直接使用GenericJackson2JsonRedisSerializer，就可以实现在redis内的json存储以及获取
     *  2.使用FastJsonRedisSerializer默认是不支持自动类型转换，因为可能被坏人利用，如果需要建议指定对应路径(从java文件夹路径开始)，需要配置 ParserConfig.getGlobalInstance().addAccept("com.atguigu.cache.bean");
     *  3.不建议使用Jackson2JsonRedisSerializer<Object> ,会出现java.util.LinkedHashMap cannot be cast to com.atguigu.cache.bean.Employee
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //直接使用GenericJackson2JsonRedisSerializer
//        GenericJackson2JsonRedisSerializer ser = new GenericJackson2JsonRedisSerializer();
        // FastJsonRedisSerializer默认是不支持自动类型转换，因为可能被坏人利用，如果需要建议指定对应路径(从java文件夹路径开始),
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ParserConfig.getGlobalInstance().addAccept("com.atguigu.cache.bean");
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        //将缓存的数据，key 和value 进行格式化成json
        template.setDefaultSerializer(fastJsonRedisSerializer);
        return template;
    }


    /**
     * 设置cache内数据的缓存时间，只会影响注解生成的数据比如@Cacheable，并不会对自己创建的K-V产生影响，
     * @param redisTemplate
     * @return
     */
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //默认使用cacheNames作为key的前缀
        cacheManager.setUsePrefix(true);
        //设置缓存过期时间（秒）
        cacheManager.setDefaultExpiration(120);
        return cacheManager;
    }



}
