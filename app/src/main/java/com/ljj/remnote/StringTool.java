package com.ljj.remnote;

public class StringTool {
    public static String getStringFromLongWithLeadingZero(Long value, int numOfDigit) {
        String ret = String.valueOf(value);
        while (ret.length() < numOfDigit) {
            ret = '0' + ret;
        }
        return ret;
    }
}
