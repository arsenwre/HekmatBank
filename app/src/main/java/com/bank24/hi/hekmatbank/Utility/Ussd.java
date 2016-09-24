package com.bank24.hi.hekmatbank.Utility;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.bank24.hi.hekmatbank.TApplication;

/**
 * Created by User on 08/25/2016.
 */
public class Ussd {
//    String myussd;
//    public Ussd(String ussd) {
//        myussd = ussd;
//    }

    public static void callUssd(String ussd){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + ussd));
        try {
            TApplication.currentActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(TApplication.applicationContext, e.toString(), Toast.LENGTH_LONG)
                    .show();
            Log.e("TAAAG", e.toString());
        }
    }
}
