package com.example.audiomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NextActivity extends AppCompatActivity {
Button startbutton,endbutton;
TimePickerDialog timePickerDialog;
SimpleDateFormat simpleDateFormat;
String time;
AudioManager audioManager;
TextView strtym;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        startbutton=findViewById(R.id.startime);
        endbutton=findViewById(R.id.endtime);
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
                int hour=calendar.get(Calendar.HOUR);
                int minute=calendar.get(Calendar.MINUTE);
                timePickerDialog=new TimePickerDialog(NextActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm a");
                   s=simpleDateFormat.format(calendar.getTime());
                   startbutton.setText(s);

                    }
                },hour,minute,false);
                timePickerDialog.show();
                GO_TO_SILENT_MODE();
            }

        });
        endbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal=Calendar.getInstance();
                final int hour=cal.get(Calendar.HOUR);
                int minute=cal.get(Calendar.MINUTE);
                //int inst=cal.get(Calendar.AM_PM);
                timePickerDialog=new TimePickerDialog(NextActivity.this, new
                        TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                               // GO_TO_GENERAL_MODE();
                                endbutton.setText(hourOfDay+":"+minute);

                            }
                        },hour,minute,false);
                timePickerDialog.show();
                //
        }
    });
    }

    private void GO_TO_GENERAL_MODE() {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    private void GO_TO_SILENT_MODE() {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //  Toast.makeText(this, "Silent mode activated", Toast.LENGTH_SHORT).show();
        Log.d("Silent Mode", "onClick: Succesfully Silent"+audioManager);
    }
}
