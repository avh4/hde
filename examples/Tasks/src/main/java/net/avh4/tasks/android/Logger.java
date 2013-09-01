package net.avh4.tasks.android;

import android.util.Log;

public class Logger {

    public static final String TAG = "Tasks";

    public static void log(String category, String action) {
        Log.v(TAG, category + ":" + action);
//        EasyTracker.getTracker().sendEvent(category, action, null, null);
    }

    public static void log(String category, String action, String label) {
        Log.v(TAG, category + ":" + action + ":" + label);
//        EasyTracker.getTracker().sendEvent(category, action, label, null);
    }

    public static void log(Throwable e) {
        if (e == null) return;
        Log.e(TAG, e.getLocalizedMessage(), e);
//        EasyTracker.getTracker().sendException(Thread.currentThread().getName(), e, false);
    }
}
