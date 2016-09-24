package com.bank24.hi.hekmatbank;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by a.ebrahiminia on 7/19/2016.
 */
public class FinePaymentActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View myFragmentView = inflater.inflate(R.layout.activity_fine_payment, container, false);

        return myFragmentView;
    }

}
