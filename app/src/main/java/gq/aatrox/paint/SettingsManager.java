package gq.aatrox.paint;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import java.util.Objects;

public class SettingsManager {
    private final SharedPreferences settings;

    public SettingsManager(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private int getColor(String key, String defValue) {
        return Color.parseColor(settings.getString(key, defValue));
    }

    private int getInt(String key, String defValue) {
        return Integer.parseInt(Objects.requireNonNull(settings.getString(key, defValue)));
    }

    int getBackgroundColor() {
        return getColor("bg_color", "#FFFFFFFF");
    }

    public int getForegroundColor() {
        return getColor("fg_color", "#FF000000");
    }

    public boolean getPaintStyle() {
        return settings.getBoolean("paint_style", false);
    }

    public int getStrokeWidth() {
        return getInt("stroke_width", "4");
    }

    public int getPaintType() {
        return getInt("paint_type", "2");
    }

    public boolean getUseGrid() {
        return settings.getBoolean("use_grid", false);
    }

    public int getGridSize() {
        return getInt("grid_size", "16");
    }
}
