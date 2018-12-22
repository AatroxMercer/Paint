package gq.aatrox.paint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashSleep();
    }

    private void splashSleep() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(0);
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                } catch (Exception e) {
                } finally {
                    finish();
                }
            }
        }.start();
    }
}