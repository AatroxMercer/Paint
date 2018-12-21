package gq.aatrox.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PaintActivity extends AppCompatActivity {
    private boolean moving;
    private Point screenSize = new Point();
    private Point touchCoord = new Point();
    private SharedPreferences settings;

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
                touchCoord.set((int) event.getRawX(), (int) event.getRawY());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        Log.e("action", "touch");
//                        Log.e("action", "down");
                        moving = false;
                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.e("action", "up");
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        Log.e("action", "move");
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
//                    Log.e("action", "long click");
                    startActivity(new Intent(PaintActivity.this, SettingsActivity.class));
                }
                return true;
            }
        });
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("action", "click");

                getWindowManager().getDefaultDisplay().getSize(screenSize);
                Log.e("Screen", "(" + screenSize.x + ", " + screenSize.y + ")");

                int xPosition = GoldenRatio.getRange(touchCoord.x / screenSize.x);
                int yPosition = GoldenRatio.getRange(touchCoord.y / screenSize.y);
                switch (xPosition * 3 + yPosition) {
                    case -4: // UpperLeft
                        layout();
                        break;
                    case -2: // LowerLeft
                        save();
                        break;
                    case +2: // UpperRight
                        undo();
                        break;
                    case +4: // LowerRight
                        redo();
                        break;
                }
            }
        });
    }

    private void layout() {
    }

    private void save() {
    }

    private void undo() {
    }

    private void redo() {
    }
}