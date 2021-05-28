package com.payuimagesearch.util;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;


import com.payuimagesearch.BaseApp;

import java.io.InputStream;

public final class ResUtils {

    private ResUtils() {
    }

    public static String getString(@StringRes int resId) {
        return BaseApp.applicationContext().getString(resId);
    }

    public static String getString(@StringRes int resId, Object... values) {
        return String.format(BaseApp.applicationContext().getString(resId), values);
    }

    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(BaseApp.applicationContext(), resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(BaseApp.applicationContext(), resId);
    }

    public static int getDimenPixel(@DimenRes int resId) {
        return BaseApp.applicationContext().getResources().getDimensionPixelSize(resId);
    }

    public static InputStream openRawResource(@RawRes int resId){
        return BaseApp.applicationContext().getResources().openRawResource(resId);
    }
}
