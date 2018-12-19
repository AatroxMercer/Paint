package gq.aatrox.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PaintActivity extends AppCompatActivity {

    private boolean moving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        setupPaint();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupPaint() {
        final PaintView paint = findViewById(R.id.paint);
        paint.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("action", "down");
                        moving = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("action", "up");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("action", "move");
                        if (!moving) {
                            moving = true;
                        }
                        break;
                }
                return moving;
            }
        });

        paint.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (!moving) {
                    Log.e("action", "long click");
                    startActivity(new Intent(PaintActivity.this, SettingsActivity.class));
                }
                return true;
            }
        });
    }

}