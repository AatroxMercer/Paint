package gq.aatrox.paint;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

public class PaintApplication extends Application {
    private final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

    public int getForegroundColor() {
        return settings.getInt("fg_color", Color.BLACK);
    }

    public int getBackgroundColor() {
        return settings.getInt("bg_color", Color.WHITE);
    }

    public boolean getPaintStyle() {
        return settings.getBoolean("paint_style", false);
    }

    public int getStrokeWidth() {
        return settings.getInt("stroke_width", 4);
    }

    public int getPaintType() {
        return settings.getInt("paint_type", 3);
    }
}