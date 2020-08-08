package com.ljj.remnote;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class RemLog {

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RemLog.context = context;
    }

    static Context context;

    public static void LogD(String tag, String log) {
        Log.d(tag, log);
        Toast.makeText(context, "[" + tag + "]" + log, Toast.LENGTH_LONG).show();
    }

    public static void LogE(String tag, String log) {
        Log.e(tag, log);
        Toast.makeText(context, "[" + tag + "]" + log, Toast.LENGTH_LONG).show();
    }
}
