package com.bank24.hi.hekmatbank;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.ui.DpEditTextNumber;
import com.bank24.hi.hekmatbank.ui.DpTextViewNumber;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;

/**
 * Created by DP128 on 8/2/2016.
 */
public class PayBillsReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View myFragmentView = inflater.inflate(R.layout.activity_pay_bills_whit_id_fragment, container, false);

        RippleView btnPay = (RippleView) myFragmentView.findViewById(R.id.bills_whit_id_btn_pay);
        final DpEditTextNumber txtBillId = (DpEditTextNumber) myFragmentView.findViewById(R.id.txt_bill_id);
        final DpEditTextNumber txtBillPayId  = (DpEditTextNumber) myFragmentView.findViewById(R.id.txt_bill_pay_id);
        final DpTextViewNumber txtPrice = (DpTextViewNumber) myFragmentView.findViewById(R.id.pay_bill_txt_price_end_term);
//        final DpTextViewNumber totalPrice = (DpTextViewNumber) myFragmentView.findViewById(R.id.pay_bills_with_id_bills_txt_price);

        return myFragmentView;
    }
}

