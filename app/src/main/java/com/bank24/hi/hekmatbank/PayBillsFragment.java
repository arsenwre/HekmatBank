package com.bank24.hi.hekmatbank;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bank24.hi.hekmatbank.BarcodeReader.IntentIntegrator;
import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.ui.DPTextView;
import com.bank24.hi.hekmatbank.ui.DpButton;
import com.bank24.hi.hekmatbank.ui.DpEditTextNumber;
import com.bank24.hi.hekmatbank.ui.DpTextViewNumber;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;


/**
 * Created by DP128 on 8/2/2016.
 */
public class PayBillsFragment extends Fragment {

    DpEditTextNumber txtBillId;
    DpEditTextNumber txtBillPayId;
    DPTextView tozihGhabz;
    LinearLayout qrReader;
    ImageView imgGhabz;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View myFragmentView = inflater.inflate(R.layout.activity_pay_bills_mci_fragment, container, false);

        RippleView btnPay = (RippleView) myFragmentView.findViewById(R.id.bills_whit_id_btn_pay);
        txtBillId = (DpEditTextNumber) myFragmentView.findViewById(R.id.txt_bill_id);
        txtBillPayId = (DpEditTextNumber) myFragmentView.findViewById(R.id.txt_bill_pay_id);
        tozihGhabz = (DPTextView) myFragmentView.findViewById(R.id.ghabz_tozih);
        imgGhabz = (ImageView) myFragmentView.findViewById(R.id.ghabz_img);
        final DpTextViewNumber txtPrice = (DpTextViewNumber) myFragmentView.findViewById(R.id.pay_bill_txt_price_end_term);
//        final DpTextViewNumber totalPrice = (DpTextViewNumber) myFragmentView.findViewById(R.id.pay_bills_with_id_bills_txt_price);
        btnPay.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                StaticModel.billId = txtBillId.getText().toString();
                StaticModel.billPayID = txtBillPayId.getText().toString();
                showDialog("");
            }
        });
        qrReader = (LinearLayout) myFragmentView.findViewById(R.id.btn_barcode_reader);
        qrReader.setOnClickListener(new View.OnClickListener() {
            @TargetApi(23)
            @Override
            public void onClick(View v) {
                String callPermission = Manifest.permission.CAMERA;
                int hasPermission = ContextCompat.checkSelfPermission(TApplication.currentActivity, callPermission);
                String[] permissions = new String[]{callPermission};
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 1);
                } else {
//                        Toast.makeText(TApplication.currentActivity, "We already have persmission", Toast.LENGTH_SHORT).show();
                    IntentIntegrator scanIntegrator = new IntentIntegrator(TApplication.currentActivity);
                    scanIntegrator.initiateScan();
                }

            }
        });
        txtBillId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                        if (txtBillId.getText().toString().substring(11, 12).equals("1")) {
                            imgGhabz.setVisibility(View.VISIBLE);
                            tozihGhabz.setVisibility(View.VISIBLE);
                            imgGhabz.setImageResource(R.drawable.water_company_logo);
                            tozihGhabz.setText("قبض آب");
                        }
                        if (txtBillId.getText().toString().substring(11, 12).equals("2")) {
                            imgGhabz.setVisibility(View.VISIBLE);
                            tozihGhabz.setVisibility(View.VISIBLE);
                            imgGhabz.setImageResource(R.drawable.power_company_logo);
                            tozihGhabz.setText("قبض برق");
                        }
                        if (txtBillId.getText().toString().substring(11, 12).equals("5")
                                ) {
                            imgGhabz.setVisibility(View.VISIBLE);
                            tozihGhabz.setVisibility(View.VISIBLE);
                            imgGhabz.setImageResource(R.drawable.phonelogo);
                            tozihGhabz.setText(" قبض تلفن همراه");
                        }
                        if (txtBillId.getText().toString().substring(11, 12).equals("3")
                                ) {
                            imgGhabz.setVisibility(View.VISIBLE);
                            tozihGhabz.setVisibility(View.VISIBLE);
                            imgGhabz.setImageResource(R.drawable.gaslogo);
                            tozihGhabz.setText(" قبض گاز");
                        }
                        if (txtBillId.getText().toString().substring(11, 12).equals("4")
                              ) {
                            imgGhabz.setVisibility(View.VISIBLE);
                            tozihGhabz.setVisibility(View.VISIBLE);
                            imgGhabz.setImageResource(R.drawable.phonelogo);
                            tozihGhabz.setText("قبض تلفن ثابت");
                        }
                        if (txtBillId.getText().toString().substring(11, 12).equals("6") ) {
                            imgGhabz.setVisibility(View.VISIBLE);
                            tozihGhabz.setVisibility(View.VISIBLE);
                            imgGhabz.setImageResource(R.drawable.shahdarilogo);
                            tozihGhabz.setText(" قبض شهرداری");
                        }
                    }

                }
            }
        });
//        txtBillId.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//

//            }
//        });
//        txtBillId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    hideKeyboard(v);
//                }
//            }
//        });
//        txtBillPayId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    hideKeyboard(v);
//                }
//            }
//        });

        return myFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) TApplication.currentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!StaticModel.billId.equals("") && !StaticModel.billPayID.equals("")) {
            txtBillId.setText(StaticModel.billId);
            txtBillPayId.setText(StaticModel.billPayID);
            if (txtBillId.getText().toString().substring(11, 12).equals("1") &&
                    !txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                imgGhabz.setVisibility(View.VISIBLE);
                tozihGhabz.setVisibility(View.VISIBLE);
                imgGhabz.setImageResource(R.drawable.water_company_logo);
                tozihGhabz.setText("قبض آب");
            }
            if (txtBillId.getText().toString().substring(11, 12).equals("2") &&
                    !txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                imgGhabz.setVisibility(View.VISIBLE);
                tozihGhabz.setVisibility(View.VISIBLE);
                imgGhabz.setImageResource(R.drawable.power_company_logo);
                tozihGhabz.setText("قبض برق");
            }
            if (txtBillId.getText().toString().substring(11, 12).equals("5") &&
                    !txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                imgGhabz.setVisibility(View.VISIBLE);
                tozihGhabz.setVisibility(View.VISIBLE);
                imgGhabz.setImageResource(R.drawable.phonelogo);
                tozihGhabz.setText(" قبض تلفن همراه");
            }
            if (txtBillId.getText().toString().substring(11, 12).equals("3") &&
                    !txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                imgGhabz.setVisibility(View.VISIBLE);
                tozihGhabz.setVisibility(View.VISIBLE);
                imgGhabz.setImageResource(R.drawable.gaslogo);
                tozihGhabz.setText(" قبض گاز");
            }
            if (txtBillId.getText().toString().substring(11, 12).equals("4") &&
                    !txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                imgGhabz.setVisibility(View.VISIBLE);
                tozihGhabz.setVisibility(View.VISIBLE);
                imgGhabz.setImageResource(R.drawable.phonelogo);
                tozihGhabz.setText("قبض تلفن ثابت");
            }
            if (txtBillId.getText().toString().substring(11, 12).equals("6") &&
                    !txtBillId.getText().toString().equals("") && txtBillId.getText().toString().length() == 13) {
                imgGhabz.setVisibility(View.VISIBLE);
                tozihGhabz.setVisibility(View.VISIBLE);
                imgGhabz.setImageResource(R.drawable.shahdarilogo);
                tozihGhabz.setText(" قبض شهرداری");
            }
        }
    }

    private void showDialog(String type) {
        final Dialog dialog = new Dialog(TApplication.currentActivity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_payment_method);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        final DpButton billWithCard = (DpButton) dialog.findViewById(R.id.dialog_entry_info_edit_card_number);
        final DpButton billWithSeporde = (DpButton) dialog.findViewById(R.id.dialog_entry_info_edit_second_password);
//        final RippleView btnConfirm = (RippleView) dialog.findViewById(R.id.dialog_entry_info_btn_confirmation);
//        final RippleView btnCancel = (RippleView) dialog.findViewById(R.id.dialog_entry_info_btn_cancel);
//        editCardNumber.addTextChangedListener(new FourDigitCardFormatWatcher());
////        btnCancel.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                dialog.dismiss();
////            }
////        });
        billWithSeporde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        billWithCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticModel.activityType = "PayBillCard";
                Intent intent = new Intent(TApplication.currentActivity, CardManagmentActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Toast.makeText(TApplication.currentActivity, "CAMERA GRANTED", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IntentIntegrator scanIntegrator = new IntentIntegrator(TApplication.currentActivity);
                    scanIntegrator.initiateScan();
                } else {
                    Toast.makeText(TApplication.currentActivity, "CAMERA DENIED", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

//    public void hideKeyboard(View view) {
//        InputMethodManager inputMethodManager =(InputMethodManager)TApplication.currentActivity
//                .getSystemService(TApplication.applicationContext.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//
//    }

}
