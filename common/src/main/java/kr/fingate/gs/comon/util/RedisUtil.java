package kr.fingate.gs.comon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.comon.consts.enums.RedisKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    /*
     * RedisConfig에 선언된 bean에 따라 생성
     */
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper redisObjectMapper;

    public RedisUtil(@Qualifier("stringRedisTemplate") StringRedisTemplate stringRedisTemplate,
                     @Qualifier("redisObjectMapper") ObjectMapper redisObjectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisObjectMapper = redisObjectMapper;
    }
    public String getKey(long clientNo, RedisKey redisKey, String... keys) {

        String key = getKey(clientNo, redisKey, Arrays.asList(keys));
        return key;
    }
    public String getKey(long clientNo, RedisKey redisKey, List<String> keys) {

        StringBuffer sb = new StringBuffer();
        sb.delete(0, sb.length());

        sb.append(redisKey.getPath());

        if(keys.contains("*")){
            sb.append("*");
        } else {
            for(String v : keys) {
                sb.append(":").append(v);
            }
        }

        String rtn = String.valueOf(clientNo) + sb.toString();

        return rtn;
    }
    public String getKey(RedisKey redisKey, String... keys) {

        String key = getKey(redisKey, Arrays.asList(keys));
        return key;
    }
    public String getKey(RedisKey redisKey, List<String> keys) {

        StringBuffer sb = new StringBuffer();
        sb.delete(0, sb.length());

        sb.append(redisKey.getPath());

        if(keys.contains("*")){
            sb.append("*");
        } else {
            for(String v : keys) {
                sb.append(":").append(v);
            }
        }

        return sb.toString();
    }
    public boolean delete(String key){
        try {
            if(key.equals("*")){
                throw new Exception();
            }
            if(key.indexOf("*") >= 0) {
                Collection<String> keys = stringRedisTemplate.keys(key);
                stringRedisTemplate.delete(keys);
            } else {
                stringRedisTemplate.delete(key);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public List<String> getList(String key) {
        try {
            List<String> preList = getData(key, List.class);
            return preList == null ? new ArrayList<>() : preList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return Collections.emptyList();
    }
    public boolean containList(String key, String element, boolean defaultVal) {
        try {
            List<String> list = getList(key);
            return list.contains(element);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return defaultVal;
    }
    public Set<String> getSet(String key) {
        try {
            Set<String> preSet = getData(key, Set.class);
            return preSet == null ? new HashSet<>() : preSet;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return Collections.emptySet();
    }
    public <T> T getData(String key, Class<T> objectType) {
        try {
            String jsonStr = stringRedisTemplate.opsForValue().get(key);
            if(ObjectUtil.isEmpty(jsonStr)) {
                return null;
            } else {
                T obj = redisObjectMapper.readValue(jsonStr, objectType);
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setData(String key, Object obj) {
        try {
            String jsonStr = redisObjectMapper.writeValueAsString(obj);
            stringRedisTemplate.opsForValue().set(key, jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setHashData(String key, String hashKey, Object obj) {
        try {
            String jsonStr = redisObjectMapper.writeValueAsString(obj);
            stringRedisTemplate.opsForHash().put(key, hashKey, jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteHashData(String key, String hashKey) {
        try {
            stringRedisTemplate.opsForHash().delete(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
