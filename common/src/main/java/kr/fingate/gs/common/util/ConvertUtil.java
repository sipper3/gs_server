package kr.fingate.gs.common.util;

public class ConvertUtil {

    /**
     * Camel 표기법
     * @param underScore
     * @return
     */
    public static String convertToCamelCase(String underScore) {
        if (underScore == null)
            return "";
        if (underScore.indexOf('_') < 0
                && Character.isLowerCase(underScore.charAt(0))) {
            return underScore;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }

}
