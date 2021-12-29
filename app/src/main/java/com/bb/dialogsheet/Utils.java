package com.bb.dialogsheet;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;

public class Utils {
    public static boolean isColorLight(int color) {
        if (color == Color.BLACK) {
            return false;
        } else if (color == Color.parseColor("#DADADA") || color == Color.TRANSPARENT) {
            return  true;
        }

        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness < 0.4;
    }

    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        return Color.argb(alpha, red, green, blue);
    }

    public static int getTextColor(int color) {
        return  isColorLight(color) ? Color.parseColor("#DE000000"):Color.parseColor("#FFFFFFFF");
    }

    public static int getTextColorSec(int color) {
        return  isColorLight(color) ? Color.parseColor("#8A000000") : Color.parseColor("#B3FFFFFF");
    }


    public static int getWindowBackground(Context context) {
        TypedValue a = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.windowBackground, a, true);
        return a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT ? a.data : Color.parseColor("#DADADA");
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
