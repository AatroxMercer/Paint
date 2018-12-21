package gq.aatrox.paint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initSettings();

        splashSleep();
    }

    private void splashSleep() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                } catch (Exception e) {
                    Log.e("SplashActivity", Log.getStackTraceString(e));
                } finally {
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        }.start();
    }

    private void initSettings() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

    }
}