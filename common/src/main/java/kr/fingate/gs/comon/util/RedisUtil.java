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

    /**
     *  type : common
     *  Redis Key 생성
     */
    public String getKey(RedisKey redisKey, String... keys) {

        String key = getKey(redisKey, Arrays.asList(keys));
        return key;
    }

    /**
     * type : common
     * Redis Key 생성
     */
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

    /**
     * type : common
     * Redis Key 존재여부 체크
     */
    public boolean hasKey(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * type : common
     * Redis Key 만료 기간 설정
     * @param key Redis key
     * @param timeout 만료 기간
     * @param timeUnit 만료 기간 타입(초,분,시,일 등등)
     */
    public void setExpire(String key, int timeout, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * type : common
     * Redis Data 삭제
     *
     * XXX - "*" 전체 key 삭제 금지
     * 전체 key 조회 시 조회가 완료될 때까지 모든 redis 작업이 중단된다.
     */
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

    /**
     * type : List
     * Redis에 List<String> 저장
     *
     * Redis에 key로 관리되는 List를 생성 후 저장
     * key에 해당하는 List가 없다면 내부적으로 빈 list를 생성 후 저장한다.
     * 또한 key에 해당하는 List가 있다면 delete 값에 따라 초기화할지, 이어서 저장할지 결정
     */
    public void setList(String key, boolean delete, List<String> list) {
        try {
            if(delete) {
                delete(key);
            }
            List<String> preList = getList(key);
            preList.addAll(list);

            setData(key, preList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    /**
     * type : List
     * Redis에 List<String> 조회
     *
     * Redis에 key로 관리되는 List를 조회
     * 만약 key에 해당하는 List가 없다면 내부적으로 빈 List 반환
     */
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

    /**
     * type : List
     * Redis에 List<String> 조회하여 element 포함 여부 반환
     *
     * Redis에 key로 관리되는 List를 조회하여 해당 List 안에 element가 존재하는지 확인
     *
     * @defaultVal 예기치 못한 Exception 발생 시 반환할 default 값
     * @return true : 존재, false : 미존재
     */
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

    /**
     * type : Set
     * Redis에 Set<String> 저장
     *
     * Redis에 key로 관리되는 Set을 생성 후 저장
     * key에 해당하는 Set이 없다면 내부적으로 빈 set을 생성 후 저장한다.
     * 또한 key에 해당하는 Set이 있다면 delete 값에 따라 초기화할지, 이어서 저장할지 결정
     */
    public void addSet(String key, boolean delete, String... values) {
        try {
            if(delete) {
                delete(key);
            }
            Set<String> preSet = getSet(key);
            preSet.addAll(Arrays.asList(values));

            setData(key, preSet);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    /**
     * type : Set
     * Redis에 Set<String> 조회
     *
     * Redis에 key로 관리되는 Set을 조회
     * 만약 key에 해당하는 Set이 없다면 내부적으로 빈 Set 반환
     */
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

    /**
     * type : Set
     * Redis에 Set<String> 조회하여 element 포함 여부 반환
     *
     * Redis에 key로 관리되는 Set을 조회하여 해당 List 안에 element가 존재하는지 확인
     *
     * @defaultVal 예기치 못한 Exception 발생 시 반환할 default 값
     * @return true : 존재, false : 미존재
     */
    public boolean containSet(String key, String element, boolean defaultVal) {
        try {
            Set<String> set = getSet(key);

            return set.contains(element);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return defaultVal;
    }

    /**
     * type : T
     * Redis에 저장된 Object 조회
     *
     * Redis에 key로 관리되는 Object를 조회하여 T.class로 converting하여 반환
     */
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

    /**
     * type : T
     * Redis에 Object를 저장
     *
     * Redis에 key로 관리되는 Object를 저장한다.
     */
    public void setData(String key, Object obj) {
        try {
            String jsonStr = redisObjectMapper.writeValueAsString(obj);
            stringRedisTemplate.opsForValue().set(key, jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * type : HashMap
     * Redis에 Map 형태로 Object를 조회
     *
     * Redis에 key로 관리되는 hashMap Object를 조회한다.
     */
    public Map<Object, Object> getHashData(String key) {
        try {
            return stringRedisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * type : HashMap
     * Redis에 Map 형태로 Object를 조회
     *
     * Redis에 key로 관리되는 hashMap에서 특정 field 해당하는 value를 조회한다.
     */
    public <T> T getHashDataField(String key, String field, Class<T> tClass) {
        try {
            Object object = stringRedisTemplate.opsForHash().get(key, field);
            if(ObjectUtil.isEmpty(object) || !(object instanceof String)){
                return null;
            } else {
                String str = (String)object;
                return redisObjectMapper.readValue(str, tClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * type : HashMap
     * Redis에 Map 형태로 Object를 저장
     *
     * Redis에 key로 관리되는 hashMap Object를 저장한다.
     */
    public void setHashData(String key, String hashKey, Object obj) {
        try {
            String jsonStr = redisObjectMapper.writeValueAsString(obj);
            stringRedisTemplate.opsForHash().put(key, hashKey, jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * type : HashMap
     * Redis에 Map 형태로 Object를 저장
     *
     * Redis에 key로 관리되는 hashMap Object를 저장한다.
     */
    public void deleteHashData(String key, String hashKey) {
        try {
            stringRedisTemplate.opsForHash().delete(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
