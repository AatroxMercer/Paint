package gq.aatrox.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void start(View view) {
    }

    public void help(View view) {
    }

    public void exit(View view) {
        finish();
    }
}
