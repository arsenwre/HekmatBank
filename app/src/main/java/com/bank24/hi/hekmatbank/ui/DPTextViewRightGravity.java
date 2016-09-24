package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by DP123 on 1/12/15.
 */
public class DPTextViewRightGravity extends TextView {
    public DPTextViewRightGravity(Context context) {
        super(context);
        init();
    }

    public DPTextViewRightGravity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DPTextViewRightGravity(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "IRAN Sans_0.ttf");
        setTypeface(tf, Typeface.BOLD);

        setGravity(Gravity.RIGHT| Gravity.CENTER);
    }
}
