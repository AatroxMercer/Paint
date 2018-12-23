package gq.aatrox.paint.shapes;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;

public class PathShape extends Shape {

    private Path path = new Path();
    private boolean drawing = false;

    public PathShape(int color, int strokeWidth, boolean isFilled) {
        super(color, strokeWidth, isFilled);
    }

    @Override
    public PathShape copy() {
        return new PathShape(paint.getColor(), (int) paint.getStrokeWidth(), isFilled());
    }

    @Override
    public void draw(Canvas canvas) {
        if (!drawing) {
            drawing = true;
            path.moveTo(start.x, start.y);
        }

        if (!start.equals(end)) {
            path.lineTo(end.x, end.y);
            start = new Point(end);
        }

        canvas.drawPath(path, paint);
    }
}