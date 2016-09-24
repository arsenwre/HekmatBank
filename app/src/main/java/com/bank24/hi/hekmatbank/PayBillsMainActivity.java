package com.bank24.hi.hekmatbank;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bank24.hi.hekmatbank.BarcodeReader.IntentIntegrator;
import com.bank24.hi.hekmatbank.BarcodeReader.IntentResult;
import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.ui.DPTextView;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;
import com.google.zxing.Result;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by DP128 on 8/2/2016.
 */
public class PayBillsMainActivity extends MainMenuActivity{

    Fragment fragment;
    Fragment newFragment;
    @Bind(R.id.pay_bills_ripple_pay_bills)
    RippleView rippleId;
    @Bind(R.id.pay_bills_ripple_mci)
    RippleView rippleMci;
    @Bind(R.id.pay_bills_txt_bills_with_id)
    DPTextView txtBillsWithId;
    @Bind(R.id.pay_bills_txt_mci)
    DPTextView txtMci;
    @Bind(R.id.toolbar_txt_tittle)
    DPTextView toolbarTxtTittle;
    @Bind(R.id.toolbar_img_back)
    ImageView imgBack;
    private ZXingScannerView mScannerView;
    PayBillsFragment payBillsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (StaticModel.add_bills) {
//            setTheme(R.style.NewDialog);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bills_main);
        ButterKnife.bind(this);
        TApplication.currentActivity = this;
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                StaticModel.billId = "";
                StaticModel.billPayID = "";
            }
        });
        toolbarTxtTittle.setText(R.string.mci_bills);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PayBillsFragment myFragment = new PayBillsFragment();
        fragmentTransaction.add(R.id.ly_parent_fragments, myFragment);
        fragmentTransaction.commit();

        rippleId.setOnClickListener(btnFragmentOnClickListener);
        rippleMci.setOnClickListener(btnFragmentOnClickListener);
    }

    Button.OnClickListener btnFragmentOnClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v == rippleMci) {
                newFragment = new PayBillsFragment();
                rippleMci.setBackgroundResource(R.drawable.back_mci_right_select);
                rippleId.setBackgroundResource(R.drawable.back_mci_left_unselect);
                txtBillsWithId.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                txtMci.setTextColor(ContextCompat.getColor(context, R.color.white));
                toolbarTxtTittle.setText(R.string.pay_mci_bills);
            } else if (v == rippleId) {
                newFragment = new PayBillsReportFragment();
                txtBillsWithId.setTextColor(ContextCompat.getColor(context, R.color.white));
                txtMci.setTextColor(ContextCompat.getColor(context, R.color.txt_light_gray));
                rippleId.setBackgroundResource(R.drawable.back_mci_left_select);
                rippleMci.setBackgroundResource(R.drawable.back_mci_right_unselect);
                toolbarTxtTittle.setText(R.string.pay_bills_with_id);
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.ly_parent_fragments, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult.getFormatName() != null) {
            StaticModel.billId = scanningResult.getContents().substring(0, 13);
            StaticModel.billPayID = scanningResult.getContents().substring(18,26);

        } else {
            Toast toast = Toast.makeText(TApplication.applicationContext,
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StaticModel.billId = "";
        StaticModel.billPayID = "";
    }
}
