package gq.aatrox.paint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void start(View view) {
        startActivity(new Intent(this, PaintActivity.class));
        exit(view);
    }

    public void menu(View view) {
        startActivity(new Intent(this, MenuActivity.class));
        exit(view);
    }

    public void exit(View view) {
        overridePendingTransition(0, 0);
        finish();
    }
}