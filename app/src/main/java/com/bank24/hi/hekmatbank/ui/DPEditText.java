package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by DP123 on 1/12/15.
 */
public class DPEditText extends EditText {
    private Context context;

    public DPEditText(Context context) {
        super(context);
         context=this.context;
        init();
    }

    public DPEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        context=this.context;
        init();
    }

    public DPEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        context=this.context;
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "IRAN Sans_0.ttf");
        setTypeface(tf, Typeface.NORMAL);

   }


}
