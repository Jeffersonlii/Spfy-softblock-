package fkspotify.spotifyadblock;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        block();

        finish();
        System.exit(0);

    }


    private void block(){ // blocks ad
        
        //blocks ad by restarting app
        ActivityManager am = (ActivityManager)getSystemService(Activity.ACTIVITY_SERVICE);
        am.killBackgroundProcesses("com.spotify.music");
        am.restartPackage("com.spotify.music");

        //starts spotify via intent
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("spotify:"));
        startActivity(intent);
    }



}