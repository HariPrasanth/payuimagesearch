package com.payuimagesearch.util;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

import es.dmoral.toasty.Toasty;


public final class ToastUtils {

    private ToastUtils() {
    }

    public static void showSuccessToast(Context context, String message) {
        try {
            Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
        }catch (Exception ignored){

        }
    }

    public static void showSuccessToast(Context context, @StringRes int resId) {
        try {
            Toasty.success(context, ResUtils.getString(resId), Toast.LENGTH_SHORT, true).show();
        }catch (Exception ignored){

        }
    }

    public static void showWarnToast(Context context, String message) {
        try {
            Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show();
        }catch (Exception ignored){

        }
    }

    public static void showWarnToast(Context context, @StringRes int resId) {
        try {
            Toasty.warning(context, ResUtils.getString(resId), Toast.LENGTH_SHORT, true).show();
        }catch (Exception ignored){

        }
    }

    public static void showErrorToast(Context context, String message) {
        try {
            Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
        }catch (Exception ignored){

        }
    }

    public static void showErrorToast(Context context, @StringRes int resId) {
        try {
            Toasty.error(context, ResUtils.getString(resId), Toast.LENGTH_SHORT, true).show();
        }catch (Exception ignored){

        }
    }

    public static void showInfoToast(Context context, String message) {
        try {
            Toasty.info(context, message).show();
        }catch (Exception ignored){

        }
    }

    public static void showInfoToast(Context context, @StringRes int resId) {
        try {
            Toasty.info(context, ResUtils.getString(resId)).show();
        }catch (Exception ignored){

        }
    }
}
