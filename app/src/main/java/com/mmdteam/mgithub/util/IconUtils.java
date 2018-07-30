package com.mmdteam.mgithub.util;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.IconicsDrawable;

public class IconUtils {

    private Context context;
    private Typeface typeface;

    public IconUtils(Context context) {
        this.context = context;
        typeface = Typeface.createFromAsset(this.context.getAssets(), "iconfont/iconfont.ttf");
    }

    public IconicsDrawable getDrawable(String icon) {
        IconicsDrawable drawable = new IconicsDrawable(context);
        drawable.iconText(icon, typeface);
        return drawable;
    }
}
