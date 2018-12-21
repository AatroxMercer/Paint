package gq.aatrox.paint.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class Shape {
    protected final Paint paint = new Paint();
    protected Point start, end;

    Shape(int color, int strokeWidth) {
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
    }

    abstract Shape copy();

    abstract void draw(Canvas canvas);

    public Paint getPaint() {
        return paint;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}