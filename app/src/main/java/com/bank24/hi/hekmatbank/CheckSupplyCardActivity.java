package com.bank24.hi.hekmatbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bank24.hi.hekmatbank.Model.StaticModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckSupplyCardActivity extends MainMenuActivity {
    @Bind(R.id.mojodi_card)
    RelativeLayout mojodiCard;
    @Bind(R.id.hesab_card)
    RelativeLayout hesabCard;
    @Bind(R.id.toolbar_img_back)
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_suplly_card);
        ButterKnife.bind(this);
        mojodiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckSupplyCardActivity.this, CardManagmentActivity.class);
                startActivity(intent);
                StaticModel.activityType = "CSCA";
            }
        });

        hesabCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
