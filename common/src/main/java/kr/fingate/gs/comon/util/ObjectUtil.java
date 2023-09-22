package kr.fingate.gs.comon.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.comon.vo.BaseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ObjectUtil {

    static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

    public static Boolean isEmpty(Object obj) {
        if(obj == null) return true;

        if (obj instanceof String) return "".equals(obj.toString().trim());
        else if (obj instanceof List) return ((List<?>) obj).isEmpty();
        else if (obj instanceof Map) return ((Map<?, ?>) obj).isEmpty();
        else if (obj instanceof Object[]) return Array.getLength(obj) == 0;
        else if (obj instanceof Long) return parseLong(obj) == 0;
        else if (obj instanceof Integer) return parseInt(obj) == 0;
        else return obj == null;
    }

    public static long parseLong(Object value) {
        long retLong = 0;
        if (value != null && value != "") {
            if (value instanceof BigInteger) retLong = ((BigInteger) value).longValue();
            else if(value instanceof String) retLong = Long.valueOf((String) value);
            else if( value instanceof BigDecimal) retLong = ((BigDecimal) value).longValue();
            else if(value instanceof Number) retLong = Long.valueOf( ((Number) value).longValue() );
            else throw new ClassCastException( "Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigInteger." );

        }
        return retLong;
    }

    public static int parseInt(Object value) {
        int retInt = 0;
        if (value != null && value != "") {
            if (value instanceof BigInteger) retInt = ((BigInteger) value).intValue();
            else if(value instanceof String) retInt = Integer.valueOf( (String) value );
            else if(value instanceof BigDecimal) retInt = ((BigDecimal) value).intValue();
            else if(value instanceof Number) retInt = Integer.valueOf( ((Number) value).intValue() );
            else throw new ClassCastException( "Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigInteger." );
        }
        return retInt;
    }

    public static String defaultStr(String str, String defaultStr) {
        if(isEmpty(str)) return defaultStr;
        else return str;
    }

    /**
     * <pre>
     * 객체복사 - ObjectMapper 이용, 비상속관계에서도 가능
     * </pre>
     *  @param rtnClz : return 객체의 Class
     *  @param copyObject : 복사할 Object
     */
    public static <T> T getClone(Class<T> rtnClz, Object copyObject) {
        try {
            ObjectMapper om = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 포함하지 않는 field는 무시
            String copyStr = om.writeValueAsString(copyObject);

            // 반환할 Instance 생성
            return om.readValue(copyStr, new TypeReference<T>() {
                @Override
                public Type getType() {
                    return rtnClz;
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * String이 비어있지 않고, Number로 변환될수 있는지 검사
     */
    public static boolean isNumber(String s) {
        return !isEmpty(s) && Pattern.compile("^[0-9]+$").matcher(s).matches();
    }
}
