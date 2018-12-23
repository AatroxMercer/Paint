package gq.aatrox.paint.shapes;

import android.graphics.Canvas;

public class Path extends Shape {
    public Path(int color, int strokeWidth, boolean isFilled) {
        super(color, strokeWidth, isFilled);
    }

    @Override
    public Path copy() {
        return new Path(paint.getColor(), (int) paint.getStrokeWidth(), isFilled());
    }

    @Override
    public void draw(Canvas canvas) {
    }
}