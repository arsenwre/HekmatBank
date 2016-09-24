package com.bank24.hi.hekmatbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bank24.hi.hekmatbank.Model.CardTypeModel;
import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.Utility.BounceInterpolator;
import com.bank24.hi.hekmatbank.Utility.SharedPreference;
import com.bank24.hi.hekmatbank.Utility.navigation_drawer;
import com.bank24.hi.hekmatbank.ui.DpButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MainMenuActivity {

    //    @Bind(R.id.main_charge_sim)
//    RippleView chargeSim;
//    @Bind(R.id.main_sale_sim)
//    RippleView saleSim;
//    @Bind(R.id.main_internet_pak)
//    RippleView internetPack;
//    @Bind(R.id.main_news)
//    RippleView news;
//    @Bind(R.id.main_pay_bills)
//    RippleView payBills;
//    @Bind(R.id.main_quick_access)
//    RippleView quickAccess;
    @Bind(R.id.imageView)
    DpButton imageView;
    @Bind(R.id.imageView1)
    DpButton imageView1;
    @Bind(R.id.imageView4)
    DpButton imageView4;
    @Bind(R.id.imageView5)
    DpButton imageView5;
    @Bind(R.id.imageView7)
    DpButton imageView7;
    @Bind(R.id.split_img_home)
    ImageView splitImgHome;
    @Bind(R.id.toolbar_img_back)
    ImageView imgBack;
    @Bind(R.id.toolbar_img_menu)
    ImageView imgMenu;
    navigation_drawer nd;
    DrawerLayout my_drawer_layout;
    boolean bankNameFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        splitImgHome.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.guid));
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        nd = (navigation_drawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        nd.setUp(
                (DrawerLayout) findViewById(R.id.main_layout)
        );
        my_drawer_layout = (DrawerLayout) findViewById(R.id.main_layout);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        double animationDuration = getDurationValue() * 1000;
        myAnim.setDuration((long) animationDuration);
        setupAmplitudeSeekBar();
        setupFrequencySeekBar();
        setupDurationVar();
        BounceInterpolator interpolator = new BounceInterpolator(getAmplitudeValue(), getFrequencyValue());
        // Use custom animation interpolator to achieve the bounce effect
        myAnim.setInterpolator(interpolator);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                    my_drawer_layout.closeDrawer(Gravity.RIGHT);
                } else {
                    my_drawer_layout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                imageView.startAnimation(myAnim);
                Intent intent = new Intent(MainActivity.this, CheckSupplyActivity.class);
                startActivity(intent);
                StaticModel.activityType = "CSA";
            }
        });
//        imageView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                imageView.startAnimation(myAnim);
//                Intent intent = new Intent(MainActivity.this, CheckSupplyActivity.class);
//                startActivity(intent);
//                StaticModel.activityType = "CSA";
//            }
//        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.startAnimation(myAnim);
                StaticModel.activityType = "CMA";
                Intent intent = new Intent(MainActivity.this, CardManagmentActivity.class);
                startActivity(intent);
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView5.startAnimation(myAnim);
                StaticModel.activityType = "MT";
                Intent intent = new Intent(MainActivity.this, MoneyTransfer.class);
                startActivity(intent);
            }
        });


        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView4.startAnimation(myAnim);
                StaticModel.activityType = "PBMA";
                Intent intent = new Intent(MainActivity.this, PayBillsMainActivity.class);
                startActivity(intent);
            }


        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView7.startAnimation(myAnim);
                StaticModel.activityType = "Check";
                Intent intent = new Intent(MainActivity.this, FineActivity.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String bankNum[] = {"603799","710019","603769","603770","639217","621986","585983","627353",
                "589210","610433","622106","589463","628023","627412","636949","627381","627648",
        "505785","505416","639607","502229","639347","636214","639599","627488","606373","502938",
        "639346","504706","502806","502908","505809","585947"};
        String bankName[] = {"meli","meli","saderat","keshavarzi","keshavarzi","saman","tejarat",
                "tejarat","sepah","melat","parsian","refah","maskan","eghtesadnovin","hekmat","ansar"
        ,"tosesaderat","iranzamin","gardeshgary","sarmaye","pasargad","pasargad","ayande","ghavamin"
        ,"karafarin","mehr","dey","sina","shahr","shahr","taavon","khavarmiane","khavarmiane"};
        Integer bankPic[] = {R.drawable.meli,R.drawable.meli,R.drawable.saderat,R.drawable.keshavarzi,R.drawable.keshavarzi,
                R.drawable.saman,R.drawable.tejarat,R.drawable.tejarat,R.drawable.sepah,R.drawable.melat,R.drawable.parsian,
                R.drawable.refah,R.drawable.maskan,R.drawable.eghtesadnovin,R.drawable.hekmat,R.drawable.ansar
                ,R.drawable.tosesaderat,R.drawable.iranzamin,R.drawable.gardeshgary,R.drawable.sarmaye,
                R.drawable.pasargad,R.drawable.pasargad,R.drawable.ayande,R.drawable.ghavamin
                ,R.drawable.karafarin,R.drawable.mehr,R.drawable.dey,R.drawable.sina,R.drawable.shahr,
                R.drawable.shahr,R.drawable.taavon,R.drawable.khavarmiane,R.drawable.khavarmiane};
        CardTypeModel cardTypeModel = new CardTypeModel();
        SharedPreference sharedPreference = new SharedPreference();
        SharedPreferences prefs = getSharedPreferences("my_preference", MODE_PRIVATE);
        Integer auto_login_flag = prefs.getInt("Auto_Login_Flag", 0);
        if(auto_login_flag == 0){
            for (int i=0; i<bankName.length ;i++){
                cardTypeModel.cardTypName =  bankName[i];
                cardTypeModel.cardTypNum =  bankNum[i];
                cardTypeModel.cardTypPic =  bankPic[i];
                sharedPreference.addCardType(TApplication.applicationContext, cardTypeModel);
            }
            SharedPreferences settings = TApplication.applicationContext.getSharedPreferences("my_preference", 0);
            SharedPreferences.Editor ed = settings.edit();
            ed.putInt("Auto_Login_Flag", 1);
            ed.apply();
        }

//        chargeSim.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        saleSim.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Intent intent = new Intent(getApplicationContext(), SaleSimCardLevelOneActivity.class);
//                startActivity(intent);
//            }
//        });
//        internetPack.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Intent intent = new Intent(getApplicationContext(), InternetPackAvtivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        news.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
//                startActivity(intent);
//            }
//        });
//        payBills.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Intent intent = new Intent(getApplicationContext(), PayBillsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        quickAccess.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Intent intent = new Intent(getApplicationContext(), QuickAccessActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    void updateTextViewValue(TextView textView, String prefix, double value) {
        String text = String.format("%s: %.2f", prefix, value);
        textView.setText(text);
    }

    double getSeekBarValue(SeekBar seekBar, double step) {
        return ((double) seekBar.getProgress() + 1.0) / (1.0 / step);
    }

    public void didTapPlayButton(View view) {
        animateButton();
    }

    void animateButton() {
        // Load the animation
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        double animationDuration = getDurationValue() * 1000;
        myAnim.setDuration((long) animationDuration);

        // Use custom animation interpolator to achieve the bounce effect
        BounceInterpolator interpolator = new BounceInterpolator(getAmplitudeValue(), getFrequencyValue());

        myAnim.setInterpolator(interpolator);

        // Animate the button
        Button button = (Button) findViewById(R.id.imageView);
        button.startAnimation(myAnim);


        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton();
            }
        });
    }

    // Duration controls
    // ---------------

    void setupDurationVar() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_duration);
        seekBar.setProgress(19);
        updateDurationLabel();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                animateButton();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateDurationLabel();
            }
        });
    }

    void updateDurationLabel() {
        TextView textView = (TextView) findViewById(R.id.text_view_duration);
        updateTextViewValue(textView, "Duration", getDurationValue());
    }

    double getDurationValue() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_duration);
        return getSeekBarValue(seekBar, 0.1);
    }


    // Amplitude controls
    // ---------------

    void setupAmplitudeSeekBar() {
        final SeekBar dampingSeekBar = (SeekBar) findViewById(R.id.seek_bar_amplitude);
        dampingSeekBar.setProgress(19);
        updateAmplitudeLabel();

        dampingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                animateButton();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateAmplitudeLabel();
            }
        });
    }

    void updateAmplitudeLabel() {
        TextView textViewDamping = (TextView) findViewById(R.id.text_view_amplitude);
        updateTextViewValue(textViewDamping, "Amplitude", getAmplitudeValue());
    }

    double getAmplitudeValue() {
        final SeekBar dampingSeekBar = (SeekBar) findViewById(R.id.seek_bar_amplitude);
        return getSeekBarValue(dampingSeekBar, 0.01);
    }


    // Frequency controls
    // ---------------

    void setupFrequencySeekBar() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_frequency);
        seekBar.setProgress(40);
        updateFrequencyLabel();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                animateButton();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateFrequencyLabel();
            }
        });
    }

    void updateFrequencyLabel() {
        TextView textView = (TextView) findViewById(R.id.text_view_frequency);
        updateTextViewValue(textView, "Frequency", getFrequencyValue());
    }

    double getFrequencyValue() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_frequency);
        return getSeekBarValue(seekBar, 0.5);
    }

}
