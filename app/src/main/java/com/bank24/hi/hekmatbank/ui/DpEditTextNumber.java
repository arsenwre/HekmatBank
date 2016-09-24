package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by DP128 on 7/12/2016.
 */
public class DpEditTextNumber extends EditText {
    private Context context;

    public DpEditTextNumber(Context context) {
        super(context);
        context=this.context;
        init();
    }

    public DpEditTextNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        context=this.context;
        init();
    }

    public DpEditTextNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        context=this.context;
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "BHOMA_3.ttf");
        setTypeface(tf, Typeface.NORMAL);

    }


}
