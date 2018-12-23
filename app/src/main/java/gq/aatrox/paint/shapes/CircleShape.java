package gq.aatrox.paint.shapes;

import android.graphics.Canvas;

public class CircleShape extends Shape {
    public CircleShape(int color, int strokeWidth, boolean isFilled) {
        super(color, strokeWidth, isFilled);
    }

    @Override
    public CircleShape copy() {
        return new CircleShape(paint.getColor(), (int) paint.getStrokeWidth(), isFilled());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(start.x, start.y, (float) Math.hypot(start.x - end.x, start.y - end.y), paint);
    }
}