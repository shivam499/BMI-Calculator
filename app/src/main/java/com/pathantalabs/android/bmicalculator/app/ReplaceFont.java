package com.pathantalabs.android.bmicalculator.app;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import java.lang.reflect.Field;

class ReplaceFont {

    static void ReplaceDefaultFont(Context context){
        Typeface chooseFont = Typeface.createFromAsset(context.getAssets(), "Quicksand-Light.ttf");
        replaceFont(chooseFont);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void replaceFont(Typeface chooseFont) {

        try {
            Field myField = Typeface.class.getDeclaredField("SERIF");
            myField.setAccessible(true);
            myField.set(null,chooseFont);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
