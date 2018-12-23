package gq.aatrox.paint.shapes;

import android.graphics.Canvas;

public class LineShape extends Shape {
    public LineShape(int color, int strokeWidth, boolean isFilled) {
        super(color, strokeWidth, isFilled);
    }

    @Override
    public LineShape copy() {
        return new LineShape(paint.getColor(), (int) paint.getStrokeWidth(), isFilled());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(start.x, start.y, end.x, end.y, paint);
    }
}