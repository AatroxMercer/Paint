package gq.aatrox.paint.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gq.aatrox.paint.R;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void start(View view) {
        startActivity(new Intent(this, PaintActivity.class));
        finish();
    }

    public void help(View view) {
        startActivity(new Intent(this, HelpActivity.class));
        finish();
    }

    public void exit(View view) {
        finish();
    }
}