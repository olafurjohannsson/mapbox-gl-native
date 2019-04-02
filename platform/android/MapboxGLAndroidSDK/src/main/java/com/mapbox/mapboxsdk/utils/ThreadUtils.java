package com.mapbox.mapboxsdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.exceptions.CalledFromWorkerThreadException;

public class ThreadUtils {

  private static Boolean debug;

  /**
   * Validates if execution is running on the main thread.
   *
   * @param context context consuming the sdk
   * @param origin  the origin of the execution
   */
  public static void checkThread(@NonNull Context context, @NonNull String origin) {
    if (debug == null) {
      debug = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    if (debug) {
      if (Looper.myLooper() != Looper.getMainLooper()) {
        throw new CalledFromWorkerThreadException(
          String.format("%s interactions should happen on the UI thread.", origin)
        );
      }
    }
  }
}
