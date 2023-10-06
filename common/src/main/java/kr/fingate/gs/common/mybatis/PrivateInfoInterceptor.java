package kr.fingate.gs.common.mybatis;

import kr.fingate.gs.common.annotation.*;
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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Intercepts({
        @Signature(
                type= Executor.class,
                method="query",
                args= {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
                type= Executor.class,
                method="update",
                args= {MappedStatement.class, Object.class})
})
public class PrivateInfoInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        try {
            /* dao method에 Encrypt annotation이 있는지 체크하여 annotation이 있는 경우에만 암호화 여부 체크 */
            Object[] args = invocation.getArgs();
            MappedStatement mapped = (MappedStatement) args[0];
            String mappedId = mapped.getId();
            String className = mappedId.substring(0, mappedId.lastIndexOf("."));
            String daoMethodName = mappedId.substring(mappedId.lastIndexOf(".") + 1);

            log.debug("className : {}", className);
            log.debug("methodName : {}", daoMethodName);
            Class c = Class.forName(className);
            Method[] methods = c.getDeclaredMethods();
            Method daoMethod = Arrays.stream(methods).filter(method1 -> method1.getName().equals(daoMethodName)).findAny().orElse(null);

            assert daoMethod != null;
            PrivateSql privateSqlAn = daoMethod.getDeclaredAnnotation(PrivateSql.class);
            if(!(privateSqlAn == null || (!privateSqlAn.masking() && !privateSqlAn.encrypt() && !privateSqlAn.decrypt()))) {
                if(methodName.equals("update") && privateSqlAn.encrypt()) {
                    Object param = args[1];
                    encrypt(param);
                }else if(methodName.equals("query")) {
                    Object resultSet = invocation.proceed();
                    if(resultSet != null) {
                        if(resultSet instanceof List<?> list) {
                            for(Object obj : list) {
                                privateInfoMark(obj);
                            }
                        } else {
                            privateInfoMark(resultSet);
                        }
                    }
                    return resultSet;
                }
            }

        }catch (Exception e) {
            log.error("", e);
        }
        return invocation.proceed();
    }

    private void privateInfoMark(Object obj) {

        Field[] fields = getFields(obj.getClass());

        Arrays.stream(fields)
                .forEach(f -> {
                    if(String.class.equals(f.getType())) {

                        f.setAccessible(true);

                        try {
                            String value = "";

                            if(f.get(obj) != null && !f.get(obj).equals("")) {

                                value = (String)f.get(obj);

                                /* 복호화 annotation이 있으면 복호화 처리 */
                                Decrypt decryptAn = f.getDeclaredAnnotation(Decrypt.class);
                                if(decryptAn != null && decryptAn.value()){
                                    value = EncryptionUtil.aesDecode(value);
                                }

                                /* 암호화 annotation이 있으면 암호화 처리 */
                                Encrypt encryptAn = f.getDeclaredAnnotation(Encrypt.class);
                                if(encryptAn != null && encryptAn.value()){
                                    value = EncryptionUtil.aesEncode(value);
                                }

                                /* Masking annotation이 있으면 Masking 처리 */
                                Masking piAn = f.getDeclaredAnnotation(Masking.class);
                                if(piAn == null) {
                                    return;
                                }

                                if (piAn.value() == MaskingType.IDNTN) {
                                    value = MaskingUtil.maskIdntn(value);
                                } else if (piAn.value() == MaskingType.TEL) {
                                    value = MaskingUtil.maskTel(value);
                                } else if (piAn.value() == MaskingType.LINE_TEL) {
                                    value = MaskingUtil.maskTel(value);
                                } else if (piAn.value() == MaskingType.NAME) {
                                    value = MaskingUtil.maskName(value);
                                } else if (piAn.value() == MaskingType.CARD) {
                                    value = MaskingUtil.maskCard(value);
                                } else if (piAn.value() == MaskingType.ACCOUNT) {
                                    value = MaskingUtil.maskAccount(value);
                                } else if(piAn.value() == MaskingType.EMAIL) {
                                    value = MaskingUtil.maskEmail(value);
                                } else if (piAn.value() == MaskingType.ADRES) {
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

    private void encrypt(Object obj) {

        Field[] fields = getFields(obj.getClass());

        Arrays.stream(fields)
                .filter(f -> f.getDeclaredAnnotation(Encrypt.class) != null && String.class.equals(f.getType()))
                .forEach(f -> {
                    f.setAccessible(true);
                    try {
                        String value = "";
                        if(f.get(obj) != null) {
                            value = EncryptionUtil.aesEncode((String) f.get(obj));
                            f.set(obj, value);
                        }

                    } catch (Exception e) {
                        log.error(e.getMessage());
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
