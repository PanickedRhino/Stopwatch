package com.example.george.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class StopwatchActivity extends AppCompatActivity {
    TextView out;
    long startTime;
    boolean running = false;
    long millis = 0;
    public long seconds, hours, minutes, remmil, secs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        out = (TextView)findViewById(R.id.textView);
        runTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stopwatch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.exit(0);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStartClick(View v){

        if(millis != 0){
            startTime = System.currentTimeMillis() - millis;
        }
        else{
            startTime = System.currentTimeMillis();
        }
        running = true;
    }
    public void onStopClick(View v){
        running = false;
    }
    public void onResetClick(View v){
        running = false;
        millis = 0;

    }

    public void runTimer(){
        final Handler h = new Handler();
        h.post(new Runnable(){
        @Override
        public void run(){
             seconds = millis / 1000L;
             hours = seconds / 3600L;
             minutes = (seconds % 3600L) / 60L;
             secs = seconds % 60L;
             remmil = millis % 1000L;
            out.setText(String.format("%d:%02d:%02d:%03d", hours, minutes, secs, remmil));
        if(running)
            millis = (System.currentTimeMillis() - startTime);
        h.postDelayed(this, 1);
        }
        });
    }
}
