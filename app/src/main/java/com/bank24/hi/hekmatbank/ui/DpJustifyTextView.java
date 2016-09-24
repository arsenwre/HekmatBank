package com.bank24.hi.hekmatbank.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class DpJustifyTextView extends TextViewEx {


	public DpJustifyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DpJustifyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DpJustifyTextView(Context context) {
		super(context);
		init();
	}

	public void init() {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "IRAN Sans_0.ttf");
			setTypeface(tf, Typeface.BOLD);
		}
	}

}