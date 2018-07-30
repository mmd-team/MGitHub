package cn.weiyii.uilib.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class UITabSegment extends HorizontalScrollView {
    public UITabSegment(Context context) {
        super(context);
    }

    public UITabSegment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UITabSegment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UITabSegment(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
