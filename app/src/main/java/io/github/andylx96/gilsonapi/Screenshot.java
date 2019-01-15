package io.github.andylx96.gilsonapi;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Kiad on 4/24/2018.
 */

public class Screenshot {
    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b= Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }
    public static Bitmap takescreenshotOfRootView(View v){
        return takescreenshot(v.getRootView());
    }
}