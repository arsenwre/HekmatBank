package com.bank24.hi.hekmatbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.app.Dialog;


import android.support.v7.widget.Toolbar;

import com.bank24.hi.hekmatbank.R;
import com.bank24.hi.hekmatbank.ui.DPTextView;

/**
 * Created by sarah Esmaeilzadeh on 6/19/2016.
 */
public abstract class MainMenuActivity extends AppCompatActivity {
    public Context context;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        activity = this;
        context = MainMenuActivity.this;
        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
    }
    @Override
    public void setContentView(int layoutResID) {
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main_menu, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DPTextView txtTittle = (DPTextView) findViewById(R.id.toolbar_txt_tittle);
        DPTextView txtConfirmation = (DPTextView) findViewById(R.id.toolbar_txt_confirmation);
        ImageView imgBack = (ImageView) findViewById(R.id.toolbar_img_back);
        ImageView imgStar = (ImageView) findViewById(R.id.split_img_star);
        ImageView splitImgHome = (ImageView) findViewById(R.id.split_img_home);
        LinearLayout splitLyMore = (LinearLayout) findViewById(R.id.split_ly_more);
        LinearLayout splitLyGuid = (LinearLayout) findViewById(R.id.split_ly_guid);
        LinearLayout splitLyMessage = (LinearLayout) findViewById(R.id.split_ly_message);
        ImageView toolbarImgInfo = (ImageView) findViewById(R.id.toolbar_img_info);
        txtTittle.setText(R.string.firstStar);
        txtConfirmation.setVisibility(View.GONE);
        imgBack.setVisibility(View.VISIBLE);

//        toolbarImgInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogGuid();
//            }
//        });

//        imgStar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),QuickAccessActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        splitImgHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        splitLyMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MoreActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        splitLyGuid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), GuidActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        splitLyMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
//                startActivity(intent);
//            }
//        });
    }

//    public void showDialogGuid()
//    {
//        final Dialog dialog = new Dialog(MainMenuActivity.this , R.style.NewDialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_guid);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        ImageView imgClose = (ImageView) dialog.findViewById(R.id.dialog_guid_img_close);
//        WebView webView = (WebView) findViewById(R.id.dialog_guid_webView);
//        DPTextView txtTittle = (DPTextView) findViewById(R.id.dialog_guid_txt_tittle);
//        imgClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
}
