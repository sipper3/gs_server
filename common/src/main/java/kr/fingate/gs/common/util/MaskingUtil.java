package kr.fingate.gs.common.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingUtil {

    /**
     * 지정된 길이만큼 문자열 마스킹
     * e.g. 홍길동 -> 홍길*
     *
     * @param nakedStringLen 마스킹하지 않을 문자의 길이
     * @param mask 마스킹할 문자
     * @return nakedString
     */
    public static String maskString(String str, int nakedStringLen, String mask) {
        if (str == null) {
            return null;
        }
        if(str.length() > nakedStringLen) {
            str = str.substring(0,nakedStringLen) + String.valueOf(mask).repeat(Math.max(0, str.length() - nakedStringLen));
        }
        return str;
    }

    /**
     * 이름 문자열 마스킹
     *
     * @param str
     * @return
     */
    public static String maskName(String str) {
        if (str == null) return null;
        str = str.trim();
        StringBuilder replaceString = new StringBuilder(str);
        String pattern = "";

        if(str.length() == 2) {
            pattern = "^(.)(.+)$";
        } else {
            pattern = "^(.)(.+)(.)$";
        }

        // 영문이름
        if(ObjectUtil.isNumEn(str.replaceAll("\\p{Z}", ""))) {
            if(str.length() > 5) {
                return maskString(str, 4, "*");
            }
        }

        // 한글이름
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        if(matcher.matches()) {
            replaceString = new StringBuilder();

            for(int i=1;i<=matcher.groupCount();i++) {
                String replaceTarget = matcher.group(i);
                if(i == 2) {
                    char[] c = new char[replaceTarget.length()];
                    Arrays.fill(c, '*');

                    replaceString.append(String.valueOf(c));
                } else {
                    replaceString.append(replaceTarget);
                }

            }
        }

        return replaceString.toString();
    }

    /**
     * 부서명 문자열 마스킹
     *
     * @param str
     * @return
     */
    public static String maskOgniName(String str) {
        if (str == null) return "";
        str = str.trim();
        String pattern = "^(.{2})(.+)$";
        return str.replaceAll(pattern, "$1****");
    }

    /**
     * 항목번호(상담번호,db번호,고객번호등) 마스킹
     * 뒤에 3자리 마스킹
     *
     * @param str
     * @return
     */
    public static String maskItemNo(String str) {
        if (str == null) return "";
        str = str.trim();

        String pattern = "^(.+)(.{3})$";
        return str.replaceAll(pattern, "$1***");
    }

    /**
     * 주민번호 문자열 마스킹 (뒤 5~6자리 마스킹)
     *
     * @param str
     * @return
     */
    public static String maskIdntn(String str) {
        if (str == null) return "";
        str = str.trim();

        if (str.length() == 13 || str.length() == 14) { // 주민등록번호,법인등록번호
            String last_6_char_pattern = "(.{6}$)";
            return str.replaceAll(last_6_char_pattern, "******");
        } else {
            return str;
        }
    }

    /**
     * 이메일 문자열 마스킹 (앞에 2자리 남기고 마스킹)
     *
     * @param str
     * @return
     */
    public static String maskEmail(String str) {
        if (str == null) return "";
        str = str.trim();
        String pattern = "([\\w.]{2})(?:[\\w.]*)(@.*)";
        return str.replaceAll(pattern, "$1****$2");
    }

    /**
     * 카드 문자열 마스킹 (앞에 5자리 남기고 마스킹)
     *
     * @param str
     * @return
     */
    public static String maskCard(String str) {
        if (str == null) return "";
        str = str.trim();
        boolean inHyphen = true;
        if (!str.contains("-")) {
            inHyphen = false;
            String regex = "(\\d{4})(\\d{4})(\\d{4})(\\d{4})";
            str = str.replaceAll(regex, "$1-$2-$3-$4");
        }


        String pattern = "(\\d{4})-(\\d{1})(\\d{3})-(\\d{4})-(\\d{4})";
        str = str.replaceAll(pattern, "$1-$2***-****-****");

        if(!inHyphen) {
            str = str.replaceAll("-", "");
        }

        return str;
    }

    /**
     * 계좌번호 문자열 마스킹 (앞에 5자리 남기고 마스킹)
     *
     * @param str
     * @return
     */
    public static String maskAccount(String str) {
        if (str == null) return "";
        str = str.trim();
        StringBuilder newStr = new StringBuilder();
        int viewCnt = 0;
        for (char ch: str.toCharArray()) {
            if(ch == '-') {
                newStr.append(ch);
            } else {
                viewCnt++;
                if(viewCnt > 5) {
                    newStr.append("*");
                } else {
                    newStr.append(ch);
                }
            }
        }

        return newStr.toString();
    }


    /**
     * 전화 문자열 마스킹 (가운데 3~4자리 마스킹)
     *
     * @param str 마스킹되지 않은 데이터 ex) 010-1234-5678 or 01012345678
     * @return 010-****-5678
     */
    public static String maskTel(String str) {
        if (str == null) return "";

        str = str.trim();

        boolean inHyphen = true;
        if (!str.contains("-")) {
            inHyphen = false;
            String regex = "^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})(\\d{3,4})(\\d{4}$)";
            str = str.replaceAll(regex, "$1-$2-$3");
        }


        String pattern = "^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-(\\d{3,4})-(\\d{4}$)";
        str = str.replaceAll(pattern, "$1-****-$3");

        if(!inHyphen) {
            str = str.replaceAll("-", "");
        }

        return str;
    }

    /**
     * 주소 문자열 마스킹 (숫자/영어 마스킹)
     *
     * @param str 주소정보
     * @return 
     */
    public static String maskAdres(String str) {
        if (str == null) return "";
        str = str.trim();

        String regex = "[\\w]";
        str = str.replaceAll(regex, "*");

        return str;
    }

}
