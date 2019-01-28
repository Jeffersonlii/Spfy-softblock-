package jeffersonliuoft.softblocker;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class activity_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.onBtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noti();

            }
        });

        Button btn2 = findViewById(R.id.offBtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noti();
            }
        });

        Button helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_main.this, helper.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        block();// problem with get extras
        if(intent.getStringExtra("methodName").equals("myMethod")){
            Log.d("ajeff", "onNewIntent: ");
            block();
        }
    }

    private void noti() {
        notif n = new notif();
        n.createNotification("noti",this);
    }

    protected void block(){
        //pause the music
        Intent i = new Intent("com.spotify.mobile.android.ui.widget.PLAY");
        i.putExtra("command", "pause");
        sendBroadcast(i);

        SystemClock.sleep(100);

        //blocks ad by restarting app
        ActivityManager am = (ActivityManager)getSystemService(Activity.ACTIVITY_SERVICE);
        am.killBackgroundProcesses("com.spotify.music");
        am.restartPackage("com.spotify.music");

        //starts spotify via intent
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("spotify:"));
        startActivity(intent);

        SystemClock.sleep(100);
        //resume music



    }
}
