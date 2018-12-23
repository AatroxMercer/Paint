package gq.aatrox.paint.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gq.aatrox.paint.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                } catch (Exception ignored) {
                } finally {
                    finish();
                }
            }
        }.start();
    }
}