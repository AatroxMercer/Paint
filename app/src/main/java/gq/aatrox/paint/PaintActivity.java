package gq.aatrox.paint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import gq.aatrox.paint.shapes.Circle;
import gq.aatrox.paint.shapes.Line;
import gq.aatrox.paint.shapes.Path;
import gq.aatrox.paint.shapes.Rectangle;
import gq.aatrox.paint.shapes.Shape;

public class PaintActivity extends AppCompatActivity {
    private boolean moving;
    private Shape shape;
    private Point screenSize = new Point();
    private Point touchCoord = new Point();
    private SettingsManager settings;
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        settings = new SettingsManager(this);
        setupPaint();
    }

    private void setupShape() {
        switch (settings.getPaintType()) {
            case 0:
                shape = new Circle(settings.getForegroundColor(), settings.getStrokeWidth(), settings.getPaintStyle());
                break;
            case 1:
                shape = new Line(settings.getForegroundColor(), settings.getStrokeWidth(), settings.getPaintStyle());
                break;
            case 2:
                shape = new Path(settings.getForegroundColor(), settings.getStrokeWidth(), settings.getPaintStyle());
                break;
            case 3:
                shape = new Rectangle(settings.getForegroundColor(), settings.getStrokeWidth(), settings.getPaintStyle());
                break;
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setupPaint() {
        paintView = findViewById(R.id.paint);
        paintView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touchCoord.set((int) event.getRawX(), (int) event.getRawY());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        Log.e("action", "down");
                        setupShape();
                        shape.start = new Point(touchCoord);
                        Log.e("start", "(" + shape.start.x + ", " + shape.start.y + ")");
                        moving = false;
                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.e("action", "up");
                        shape = shape.copy();
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        Log.e("action", "move");
                        if (!moving) {
                            moving = true;
                            paintView.activeList.add(shape);
                            paintView.deletedList.clear();
                        }

                        Log.e("start", "(" + shape.start.x + ", " + shape.start.y + ")");
                        shape.end = new Point(touchCoord);
                        Log.e("__end", "(" + shape.end.x + ", " + shape.end.y + ")");
                        paintView.invalidate();
                        break;
                }
                return moving;
            }
        });
        paintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("action", "click");

                getWindowManager().getDefaultDisplay().getSize(screenSize);

                int xPosition = GoldenRatio.getRange((double)(touchCoord.x) / screenSize.x);
                int yPosition = GoldenRatio.getRange((double)(touchCoord.y) / screenSize.y);
                switch (xPosition * 3 + yPosition) {
                    case -4: // UpperLeft
                        layout();
                        break;
                    case -2: // LowerLeft
                        start();
                        break;
                    case +2: // UpperRight
                        undo();
                        break;
                    case +4: // LowerRight
                        redo();
                        break;
                }
                paintView.invalidate();
            }
        });
    }

    private void layout() {
    }

    private void start() {
        startActivity(new Intent(PaintActivity.this, SettingsActivity.class));
    }

    private void undo() {
        if (!paintView.activeList.isEmpty()) {
            int activeListLastIndex = paintView.activeList.size() - 1;

            paintView.deletedList.add(paintView.activeList.get(activeListLastIndex));
            paintView.activeList.remove(activeListLastIndex);
        }
    }

    private void redo() {
        if (!paintView.deletedList.isEmpty()) {
            int deletedListLastIndex = paintView.deletedList.size() - 1;

            paintView.activeList.add(paintView.deletedList.get(deletedListLastIndex));
            paintView.deletedList.remove(deletedListLastIndex);
        }
    }
}