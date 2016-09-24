package com.bank24.hi.hekmatbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoneyTransfer extends MainMenuActivity {


    @Bind(R.id.cardtocard)
    RelativeLayout cardToCard;
    @Bind(R.id.toolbar_img_back)
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);
        ButterKnife.bind(this);
        cardToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyTransfer.this, CardManagmentActivity.class);
                startActivity(intent);
                StaticModel.activityType = "CardToCard";
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
