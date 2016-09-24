package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DP128 on 7/5/2016.
 */
public class DpTextViewBold extends TextView {
    public DpTextViewBold(Context context) {
        super(context);
        init();
    }

    public DpTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DpTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "IRAN Sans Bold_0.ttf");
        setTypeface(tf, Typeface.NORMAL);
    }
}
