package gq.aatrox.paint.shapes;

import android.graphics.Canvas;

public class Line extends Shape {
    public Line(int color, int strokeWidth, boolean isFilled) {
        super(color, strokeWidth, isFilled);
    }

    @Override
    public Line copy() {
        return new Line(paint.getColor(), (int) paint.getStrokeWidth(), isFilled());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(start.x, start.y, end.x, end.y, paint);
    }
}