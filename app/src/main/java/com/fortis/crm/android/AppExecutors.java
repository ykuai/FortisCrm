package com.fortis.crm.android;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class AppExecutors {

    public static final Executor DISK_IO = Executors.newSingleThreadExecutor();
    public static final Executor NETWORK_IO = Executors.newSingleThreadExecutor();
    public static final Executor MAIN_THREAD = new MainThreadExecutor();

    private AppExecutors() {
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
