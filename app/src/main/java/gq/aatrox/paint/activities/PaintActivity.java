package gq.aatrox.paint.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import gq.aatrox.paint.tools.GoldenRatio;
import gq.aatrox.paint.views.PaintView;
import gq.aatrox.paint.R;
import gq.aatrox.paint.tools.SettingsManager;
import gq.aatrox.paint.shapes.Circle;
import gq.aatrox.paint.shapes.Line;
import gq.aatrox.paint.shapes.Path;
import gq.aatrox.paint.shapes.Rectangle;
import gq.aatrox.paint.shapes.Shape;

public class PaintActivity extends AppCompatActivity {
    private boolean drawing;
    private Shape shape;
    private Point screenSize = new Point();
    private Point touchCoord = new Point();
    private SettingsManager settings;
    private PaintView paintView;
    private Date down, up;
    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ArrayList<String> permissionList = new ArrayList<>();

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
                touchCoord.set(format(event.getRawX()), format(event.getRawY()));
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setupShape();
                        shape.start = new Point(touchCoord);
                        shape.end = new Point(touchCoord);
                        drawing = false;
                        down = new Date();
                        break;
                    case MotionEvent.ACTION_UP:
                        shape = shape.copy();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!drawing) {
                            up = new Date();
                            double distance = Math.hypot(shape.end.x - shape.start.x, shape.end.y - shape.start.y);
                            if (drawing = distance > 64 || up.getTime() - down.getTime() > 128) {
                                paintView.activeList.add(shape);
                                paintView.deletedList.clear();
                            }
                        }
                        shape.end = new Point(touchCoord);
                        paintView.invalidate();
                        break;
                }
                return drawing;
            }
        });
        paintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindowManager().getDefaultDisplay().getSize(screenSize);
                int xPosition = GoldenRatio.getRange((double) (touchCoord.x) / screenSize.x);
                int yPosition = GoldenRatio.getRange((double) (touchCoord.y) / screenSize.y);
                switch (xPosition * 3 + yPosition) {
                    case -4: // UpperLeft
                        save();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {}

    private void save() {
        permissionList.clear();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (permissionList.isEmpty()) {
            final EditText editText = new EditText(this);
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle(R.string.title_save).setView(editText);
            saveDialog.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                @SuppressLint("WrongCall")
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bitmap bitmap = Bitmap.createBitmap(screenSize.x, screenSize.y, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    paintView.onDraw(canvas);
                    canvas.save();
                    canvas.restore();
                    String filename = editText.getText().toString();
                    if (!Pattern.matches("^.*\\.png$", filename)) {
                        filename += ".png";
                    }

                    File file = new File(Environment.getExternalStorageDirectory().getPath(), filename);
                    Log.e("file", file.getPath());
                    Log.e("file", String.valueOf(file.exists()));
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        Log.e("file", String.valueOf(file.exists()));
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        Log.e("file", String.valueOf(file.exists()));
                        fileOutputStream.close();
                        Log.e("file", String.valueOf(file.exists()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Log.e("file", String.valueOf(file.exists()));
                    Toast.makeText(PaintActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            });
            saveDialog.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(PaintActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                }
            });
            saveDialog.show();
        } else {
            String[] requestPermissions = permissionList.toArray(new String[0]);
            ActivityCompat.requestPermissions(this, requestPermissions,0);
        }
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

    private int format(float coord) {
        int size = settings.getUseGrid() ? settings.getGridSize() : 1;
        return Math.round(coord / size) * size;
    }
}