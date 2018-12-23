package gq.aatrox.paint.shapes;

import android.graphics.Canvas;

public class RectangleShape extends Shape {
    public RectangleShape(int color, int strokeWidth, boolean isFilled) {
        super(color, strokeWidth, isFilled);
    }

    @Override
    public RectangleShape copy() {
        return new RectangleShape(paint.getColor(), (int) paint.getStrokeWidth(), isFilled());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.max(start.x, end.x), Math.max(start.y, end.y), paint);
    }
}
