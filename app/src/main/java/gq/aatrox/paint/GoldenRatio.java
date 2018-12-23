package gq.aatrox.paint;

public class GoldenRatio {
    private static final double LOWER = (3 - Math.sqrt(5)) / 2;
    private static final double UPPER = (Math.sqrt(5) - 1) / 2;

    public static int getRange(double ratio) {
        return ratio <= LOWER ? -1 : ratio < UPPER ? 0 : 1;
    }
}