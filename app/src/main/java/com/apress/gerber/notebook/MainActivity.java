package com.apress.gerber.notebook;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private  static final String TAG = "MainActivity";
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button startIntentService;
    private Button button_add;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder)iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgree();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LongRunningService.class);
        startService(intent);
        //jump to another activity
        button_add = (Button)findViewById(R.id.add_button);
        startService=(Button)findViewById(R.id.start_service);
        stopService = (Button)findViewById(R.id.stop_service);
        bindService = (Button)findViewById(R.id.bind_service);
        unbindService = (Button)findViewById(R.id.unbind_service);
        startIntentService = (Button)findViewById(R.id.start_intent_service);
        button_add.setOnClickListener(this);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                Log.d(TAG, "the start_service"+Thread.currentThread().getId());
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.add_button:
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(mConnection);
                break;
            case R.id.start_intent_service:
                Log.d(TAG, "start_intent_service"+Thread.currentThread().getId());
                Intent intentServce = new Intent(this, MyIntentService.class);
                startService(intentServce);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
