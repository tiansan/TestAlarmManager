package cn.amtxts.testalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTime = findViewById(R.id.etTime);
    }

    public void toRepeating(View view) {
        long time = getTime();
        Log.e("MainActivity", "toRepeating()");
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent amIntent = new Intent();
        amIntent.setAction("START_CARD_SERVICE");
        amIntent.putExtra("type", "AUTO_SERVER_CARD");
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, amIntent, 0);
        Log.e("MainActivity", "SystemClock.elapsedRealtime()" + SystemClock.elapsedRealtime());
        long triggerAtTime = SystemClock.elapsedRealtime() + time * 60 * 1000;
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, time * 60 * 1000, pi);
    }

    private long getTime() {
        String text = etTime.getText().toString();
        long time = 2;
        if (text.length()>0){
            time = Long.valueOf(text);
        }
        return time;
    }

    public void toSet(View view) {
        long time = getTime();
        Log.e("MainActivity", "toSet()");
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent amIntent = new Intent();
        amIntent.setAction("START_CARD_SERVICE");
        amIntent.putExtra("type", "AUTO_SERVER_CARD");
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, amIntent, 0);
        long triggerAtTime = SystemClock.elapsedRealtime() + time * 60 * 1000;
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
    }

    public void toRemove(View view) {
        Log.e("MainActivity", "toRemove()");
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent amIntent = new Intent();
        amIntent.setAction("START_CARD_SERVICE");
        amIntent.putExtra("type", "AUTO_SERVER_CARD");
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, amIntent, 0);
        am.cancel(pi);
    }
}
