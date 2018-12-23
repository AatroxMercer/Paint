package gq.aatrox.paint.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public abstract class Shape {
    public Point start, end;
    protected final Paint paint = new Paint();

    Shape(int color, int strokeWidth, boolean isFilled) {
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(isFilled ? Paint.Style.FILL : Paint.Style.STROKE);
    }

    public abstract Shape copy();

    public abstract void draw(Canvas canvas);

    boolean isFilled() {
        return paint.getStyle() == Paint.Style.FILL;
    }
}