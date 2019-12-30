package com.example.audiomanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int ON_DO_NOT_DISTURB_CALLBACK_CODE = 0 ;
    Button SilentButton,VibrateButton,RingButton;
AudioManager audioManager;
Button Next;
Button Count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestMutePermissions();
        Count=findViewById(R.id.btnCount);
        Count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTaskClass().execute();
            }
        });
        SilentButton=findViewById(R.id.btnSilent);
        VibrateButton=findViewById(R.id.btnVibrate);
        RingButton=findViewById(R.id.btnRing);
        Next=findViewById(R.id.btnNext);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NextActivity.class));
            }
        });
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int currentMode=audioManager.getRingerMode();
        if (currentMode==AudioManager.RINGER_MODE_NORMAL)
            RingButton.setBackgroundResource(R.color.colorAccent);
        else if (currentMode==AudioManager.RINGER_MODE_SILENT)
            SilentButton.setBackgroundResource(R.color.colorAccent);
        else if (currentMode==AudioManager.RINGER_MODE_VIBRATE)
            VibrateButton.setBackgroundResource(R.color.colorAccent);
        SilentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Silent Mode", "Silent_Method: Silent Mode is running...");
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
              //  Toast.makeText(this, "Silent mode activated", Toast.LENGTH_SHORT).show();
                SilentButton.setBackgroundResource(R.color.colorAccent);
                RingButton.setBackgroundResource(R.color.colorPrimary);
                VibrateButton.setBackgroundResource(R.color.colorPrimary);
                Log.d("Silent Mode", "onClick: Succesfully Silent"+audioManager);
            }
        });
        RingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                //Toast.makeText(this, "Ring mode activated", Toast.LENGTH_SHORT).show();
                SilentButton.setBackgroundResource(R.color.colorPrimary);
                RingButton.setBackgroundResource(R.color.colorAccent);
                VibrateButton.setBackgroundResource(R.color.colorPrimary);
            }
        });
        VibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
           //     Toast.makeText(this, "Vibrate mode activated", Toast.LENGTH_SHORT).show();
                SilentButton.setBackgroundResource(R.color.colorPrimary);
                RingButton.setBackgroundResource(R.color.colorPrimary);
                VibrateButton.setBackgroundResource(R.color.colorAccent);
            }
        });
    }
    private void requestMutePermissions() {
        try {
            if (Build.VERSION.SDK_INT < 23) {
                AudioManager audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            } else if( Build.VERSION.SDK_INT >= 23 ) {
                this.requestForDoNotDisturbPermissionOrSetDoNotDisturbForApi23AndUp();
            }
        } catch ( SecurityException e ) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestForDoNotDisturbPermissionOrSetDoNotDisturbForApi23AndUp() {

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        // if user granted access else ask for permission
        if ( notificationManager.isNotificationPolicyAccessGranted()) {
            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } else{
            // Open Setting screen to ask for permisssion
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivityForResult( intent, ON_DO_NOT_DISTURB_CALLBACK_CODE );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ON_DO_NOT_DISTURB_CALLBACK_CODE) {
            this.requestForDoNotDisturbPermissionOrSetDoNotDisturbForApi23AndUp();
        }


    }}









