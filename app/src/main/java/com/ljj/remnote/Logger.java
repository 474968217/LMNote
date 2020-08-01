package com.ljj.remnote;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class Logger {

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Logger.context = context;
    }

    static Context context;

    public static void LogD(String tag, String log) {
        Log.d(tag, log);
        Toast.makeText(context, "[" + tag + "]" + log, Toast.LENGTH_LONG).show();
    }
}
