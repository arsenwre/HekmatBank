package com.bank24.hi.hekmatbank;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.bank24.hi.hekmatbank.ui.DPTextView;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;


public class FineActivity extends MainActivity {

    public RippleView rippleInquiry;
    public RippleView rippleNegativePoint;
    public RippleView rippleFinePay;
    DPTextView txtTittle;
    DPTextView btnNegativeTxt;
    DPTextView btnInquirytxt;
    DPTextView btnPaytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);


        rippleInquiry = (RippleView) findViewById(R.id.btn_fine_inquiry);
        rippleNegativePoint = (RippleView) findViewById(R.id.btn_fine_negative_point);
        rippleFinePay = (RippleView) findViewById(R.id.btn_fine_payment);
        txtTittle = (DPTextView) findViewById(R.id.toolbar_txt_tittle);
        btnNegativeTxt = (DPTextView) findViewById(R.id.btn_fine_negative);
        btnInquirytxt = (DPTextView) findViewById(R.id.btn_fine_inquiry_txt);
        btnPaytxt = (DPTextView) findViewById(R.id.btn_fine_pay_txt);
        txtTittle.setText("استعلام کلی");

        // get an instance of FragmentTransaction from your Activity
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add a fragment
        FineAllInquiryActivity myFragment = new FineAllInquiryActivity();
        fragmentTransaction.add(R.id.fine_shared_layout, myFragment);
        fragmentTransaction.commit();

//

        rippleInquiry.setOnClickListener(btnFragmentOnClickListener);
        rippleNegativePoint.setOnClickListener(btnFragmentOnClickListener);
        rippleFinePay.setOnClickListener(btnFragmentOnClickListener);

    }

    Button.OnClickListener btnFragmentOnClickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Fragment newFragment;

            // Create new fragment
            if(v ==  rippleInquiry){
                btnInquirytxt.setTextColor(ContextCompat.getColor(context, R.color.white));
                btnNegativeTxt.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                btnPaytxt.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                rippleNegativePoint.setBackgroundColor(getResources().getColor(R.color.transparent));
                rippleInquiry.setBackgroundResource(R.drawable.back_mci_right_select);
                rippleFinePay.setBackgroundResource(R.drawable.back_mci_left_unselect);
                newFragment = new FineAllInquiryActivity();;
            }else if (v == rippleNegativePoint){
                btnNegativeTxt.setTextColor(ContextCompat.getColor(context, R.color.white));
                btnInquirytxt.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                btnPaytxt.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                rippleNegativePoint.setBackgroundColor(getResources().getColor(R.color.main_color));
                rippleInquiry.setBackgroundResource(R.drawable.back_mci_right_unselect);
                rippleFinePay.setBackgroundResource(R.drawable.back_mci_left_unselect);
                newFragment = new FineNegativePointActivity();
            }
            else
            {
                btnNegativeTxt.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                btnInquirytxt.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                btnPaytxt.setTextColor(ContextCompat.getColor(context, R.color.white));
                rippleNegativePoint.setBackgroundColor(getResources().getColor(R.color.transparent));
                rippleInquiry.setBackgroundResource(R.drawable.back_mci_right_unselect);
                rippleFinePay.setBackgroundResource(R.drawable.back_mci_left_select);
                newFragment = new FinePaymentActivity();

            }

            // Create new transaction
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fine_shared_layout, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }};

}
