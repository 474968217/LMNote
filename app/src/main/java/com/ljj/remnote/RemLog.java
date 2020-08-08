package com.ljj.remnote;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class RemLog {

    public static final Boolean USE_SYS_LOG = true;
    public static final Boolean USE_TOAST_LOG = true;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RemLog.context = context;
    }

    static Context context;

    public static void LogD(String tag, String log) {
        if (USE_SYS_LOG)
            Log.d(tag, log);
        if (USE_TOAST_LOG)
            Toast.makeText(context, "[DEBUG][" + tag + "]" + log, Toast.LENGTH_LONG).show();
    }

    public static void LogE(String tag, String log) {
        if (USE_SYS_LOG)
            Log.e(tag, log);
        if (USE_TOAST_LOG)
            Toast.makeText(context, "[ERROR][" + tag + "]" + log, Toast.LENGTH_LONG).show();
    }
}
