package com.ck.tinnydouban.modules.security.util;


import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 存储String类型数据
     *
     * @param key   键
     * @param value string类型值
     */
    public void setStrValue(String key, String value) {

        if (!StringUtils.isEmpty(value)) {
            redisTemplate.boundValueOps(key).set(value);
        }
    }

    /**
     * 获取string类型数据
     *
     * @param key 键
     * @return string类型值
     */
    public String getStrValue(String key) {
        Object value = redisTemplate.boundValueOps(key).get();
        if (value != null) {
            return (String) redisTemplate.boundValueOps(key).get();
        }
        return "";
    }


    /**
     * 存储map类型数据
     *
     * @param key  键
     * @param hash 哈希类型数据
     */
    public void setHashValue(String key, Map<String, Object> hash) {
        if (hash != null && !hash.isEmpty()) {
            redisTemplate.boundHashOps(key).putAll(hash);
        }
    }

    /**
     * 查询hash类型数据
     *
     * @param key 键
     * @return 哈希数据
     */
    public Map<String, Object> getHashValue(String key) {

        BoundHashOperations<String, String, Object> boundKey
                = redisTemplate.boundHashOps(key);
        return boundKey.entries();
    }

    /**
     * 修改hash类型内部值
     *
     * @param mainKey hash数据主key
     * @param subKey  hash数据内部key
     * @param value   hash数据内部值
     */
    public void editHashValue(String mainKey, String subKey, Object value) {
        redisTemplate.boundHashOps(mainKey).put(subKey, value);
    }


    /**
     * 获取hash数据类型内部值
     *
     * @param mainKey hash数据主key
     * @param subKey  hash数据内部key
     * @return hash数据内部值
     */
    public Object getHashSubValue(String mainKey, String subKey) {
        return redisTemplate.boundHashOps(mainKey).get(subKey);
    }


    /**
     * 设置过期时间
     *
     * @param key    键
     * @param expire 过期时间(单位/毫秒)
     */
    public void setMillsecondExpire(String key, Long expire) {
        redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
    }


    /**
     * 设置过期时间
     *
     * @param key    键
     * @param expire 过期时间(单位/秒)
     */
    public void setSecondExpire(String key, Long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }


    /**
     * 设置过期时间
     *
     * @param key    键
     * @param expire 过期时间(单位/分钟)
     */
    public void setMinuteExpire(String key, Long expire) {
        redisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }


    /**
     * 设置过期时间
     *
     * @param key  键
     * @param time 过期时间(单位/小时)
     */
    public void setHourExpire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.HOURS);
    }


    /**
     * 删除键以及对应的缓存
     *
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }


    /**
     * 检查key是否存在
     *
     * @return bool
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 根据正则查询符合条件的key集合
     *
     * @param pattern 正则条件
     * @return keySet
     */
    public Set<String> patternKeySet(String pattern) {
        return redisTemplate.keys(pattern);
    }


}
