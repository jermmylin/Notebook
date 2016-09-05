package com.apress.gerber.notebook;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/3.
 */
public class MyService extends Service {
    private static final String TAG ="MyService";
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder{

        public void startDownload(){
            Log.d(TAG, "startDownload: ");
        }
        public int getProgree(){
            Log.d(TAG, "getProgree: ");
            return 0;
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "The thread id is"+Thread.currentThread().getId());
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
