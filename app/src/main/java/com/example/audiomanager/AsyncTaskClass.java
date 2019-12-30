package com.example.audiomanager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AsyncTaskClass extends AsyncTask<Void, Void,Void> {
    Context context;
    @Override
    protected Void doInBackground(Void... voids) {
        for (int i=0;i<=9;i++){
            try
            {
                Thread.sleep(1000);
              //Toast.makeText(context, "Line printed", Toast.LENGTH_SHORT).show();
                Log.d( "doInBackground: ","Line Executed");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
