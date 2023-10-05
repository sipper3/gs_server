package kr.fingate.gs.common.mybatis;

import kr.fingate.gs.common.annotation.Masking;
import kr.fingate.gs.common.annotation.Private;
import kr.fingate.gs.common.util.EncryptionUtil;
import kr.fingate.gs.common.util.MaskingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Intercepts(@Signature(
        type= Executor.class,
        method="query",
        args= {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PrivateInfoInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object resultSet = invocation.proceed();
        if(resultSet != null) {
            List<?> list = null;
            if(resultSet instanceof List) {
                list = (List<?>)resultSet;
                for(Object obj : list) {
                    privateInfoMark(obj);
                }

            } else {
                privateInfoMark(resultSet);
            }
        }

        return resultSet;
    }

    private void privateInfoMark(Object obj) throws Exception {

        Field[] fields = getFields(obj.getClass());

        Arrays.stream(fields)
                .filter(f -> f.getDeclaredAnnotation(Private.class) != null)
                .forEach(f -> {
                    Private piAn = f.getDeclaredAnnotation(Private.class);
                    if(String.class.equals(f.getType())) {

                        f.setAccessible(true);

                        try {
                            String value = "";
                            if(f.get(obj) != null && !f.get(obj).equals("")) {

                                value = (String)f.get(obj);

                                if(piAn.encrypt()){
                                    value = EncryptionUtil.aesDecode(value);
                                }

                                if (piAn.value() == Masking.IDNTN) {
                                    value = MaskingUtil.maskIdntn(value);
                                } else if (piAn.value() == Masking.TEL) {
                                    value = MaskingUtil.maskTel(value);
                                } else if (piAn.value() == Masking.LINE_TEL) {
                                    value = MaskingUtil.maskTel(value);
                                } else if (piAn.value() == Masking.NAME) {
                                    value = MaskingUtil.maskName(value);
                                } else if (piAn.value() == Masking.CARD) {
                                    value = MaskingUtil.maskCard(value);
                                } else if (piAn.value() == Masking.ACCOUNT) {
                                    value = MaskingUtil.maskAccount(value);
                                } else if(piAn.value() == Masking.EMAIL) {
                                    value = MaskingUtil.maskEmail(value);
                                } else if (piAn.value() == Masking.ADRES) {
                                    value = MaskingUtil.maskAdres(value);
                                }

                            }

                            f.set(obj, value);

                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            log.error(e.getMessage());
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }

                });
    }

    private Field[] getFields(Class c){

        Field[] fields = c.getDeclaredFields();

        Class superClazz = c.getSuperclass();
        if(superClazz != null){
            return ArrayUtils.addAll(fields, getFields(superClazz));
        }

        return fields;
    }
}
