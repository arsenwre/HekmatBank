package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.bank24.hi.hekmatbank.R;


/**
 * Created by DP128 on 6/20/2016.
 */
public class NumberPicker extends android.widget.NumberPicker {

    public NumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View view) {
        if(view instanceof EditText){
            ((EditText) view).setTextSize(getResources().getDimension(R.dimen.number_picker_txt_size));

            Typeface type = Typeface.createFromAsset(getContext().getAssets(), "IRAN Sans_0.ttf");
            ((EditText) view).setTypeface(type);
            ((EditText) view).setTextColor(Color.parseColor("#3d3f65"));
        }
    }

}