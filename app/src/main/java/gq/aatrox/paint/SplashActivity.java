package gq.aatrox.paint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
