package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DP128 on 7/12/2016.
 */
public class DpTextViewNumber extends TextView {
    public DpTextViewNumber(Context context) {
        super(context);
        init();
    }

    public DpTextViewNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DpTextViewNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "BHOMA_3.ttf");
        setTypeface(tf, Typeface.NORMAL);
    }
}
