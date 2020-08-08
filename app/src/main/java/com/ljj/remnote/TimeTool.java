package com.ljj.remnote;

public class TimeTool {
    public final static Long MILLIS_PER_SECOND = 1000L;
    public final static Long MILLIS_PER_MINUTE = 1000L * 60;
    public final static Long MILLIS_PER_HOUR = 1000L * 60 * 60;

    public static Long getTimestampNow() {
        return (Long)System.currentTimeMillis();
    }

    public static Long getTimestampAfter(Long after) {
        return getTimestampNow() + after;
    }

    public static Long getTimestampAfterHMS(int h, int m, int s) {
        return getTimestampNow() + getTimestampFromHMS(h, m, s);
    }

    public static Long getTimestampFromHMS(int h, int m, int s) {
        return h * MILLIS_PER_HOUR + m * MILLIS_PER_MINUTE + s * MILLIS_PER_SECOND;
    }
}
