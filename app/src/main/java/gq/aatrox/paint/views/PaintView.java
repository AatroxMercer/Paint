package gq.aatrox.paint.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import gq.aatrox.paint.tools.SettingsManager;
import gq.aatrox.paint.shapes.Shape;

public class PaintView extends View {
    public ArrayList<Shape> activeList = new ArrayList<>();
    public ArrayList<Shape> deletedList = new ArrayList<>();
    private SettingsManager settings;

    public PaintView(Context context) {
        super(context);
        settings = new SettingsManager(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        settings = new SettingsManager(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        settings = new SettingsManager(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(settings.getBackgroundColor());
        for (Shape shape : activeList) {
            shape.draw(canvas);
        }
    }
}