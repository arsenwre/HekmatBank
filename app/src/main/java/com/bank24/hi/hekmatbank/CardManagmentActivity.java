package com.bank24.hi.hekmatbank;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bank24.hi.hekmatbank.Adapters.AdapterCardList;
import com.bank24.hi.hekmatbank.Model.CardModel;
import com.bank24.hi.hekmatbank.Model.CardTypeModel;
import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.Utility.FourDigitCardFormatWatcher;
import com.bank24.hi.hekmatbank.Utility.SharedPreference;
import com.bank24.hi.hekmatbank.Utility.Ussd;
import com.bank24.hi.hekmatbank.ui.DPEditText;
import com.bank24.hi.hekmatbank.ui.DPTextView;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by DP128 on 7/14/2016.
 */
public class CardManagmentActivity extends MainMenuActivity {

    @Bind(R.id.toolbar_txt_tittle)
    DPTextView toolbarTxtTittle;
    @Bind(R.id.toolbar_img_back)
    ImageView imgBack;
    ImageView imgBankType;

    //    @Bind(R.id.bottom_menu_txt)
//    DPTextView bottomMenuTxt;
    @Bind(R.id.card_manage_ripplView_adding_new_card)
    RippleView ripplViewAddingNewCard;
    private RippleView dialogBtnConfimation;
    private LinearLayout lyCardName;
    private DPEditText cardNumber;
    private DPEditText tarikh;
    private DPEditText cvv2;
    private DPEditText secondPass;
    private DPEditText edtxtCardName;
    private LinearLayout dialogMainBackground;
    String codePrice;
    String cardnum;
    String pass;
    String cv2;
    String date;
    String secondCardNumber;
    String price;
    String ussd;
    ListView lstContent;
    SharedPreference sharedPreference = new SharedPreference();
    ArrayList<CardTypeModel> CardTyps;
    public ArrayAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        TApplication.currentActivity = this;
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_managment);
        TApplication.currentActivity = this;
        ButterKnife.bind(this);
        toolbarTxtTittle.setText("مدیریت کارت");
        TApplication.currentActivity = this;
//        startService(new Intent(this, USSDService.class));
        ripplViewAddingNewCard.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                showDialogAddCard();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lstContent = (ListView) findViewById(R.id.card_managment_lst);
        SharedPreference sharedPreference = new SharedPreference();
        ArrayList<CardModel> cards = sharedPreference
                .getCards(getApplicationContext());
        adapter = new AdapterCardList(cards);
        lstContent.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void showDialogAddCard() {
        final Dialog dialog = new Dialog(CardManagmentActivity.this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_entry_banck_info);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialogBtnConfimation = (RippleView) dialog.findViewById(R.id.dialog_entry_info_btn_confirmation);
        final CheckBox infoCheckBox = (CheckBox) dialog.findViewById(R.id.dialog_entry_info_checkBox);
        lyCardName = (LinearLayout) dialog.findViewById(R.id.dialog_entry_info_ly_card_name);
        cardNumber = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_card_number);
        secondPass = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_second_password);
        tarikh = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_tarikh);
        cvv2 = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_cvv2);
        edtxtCardName = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_card_name);
        imgBankType = (ImageView) dialog.findViewById(R.id.img_bank_type);
        dialogMainBackground = (LinearLayout) dialog.findViewById(R.id.dialog_main_background);
        cardNumber.addTextChangedListener(new FourDigitCardFormatWatcher());

        infoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lyCardName.setVisibility(View.VISIBLE);
                } else {
                    lyCardName.setVisibility(View.GONE);
                }
            }
        });
        cardNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String card_num = cardNumber.getText().toString().trim().replaceAll("\\-", "");
                    if (!card_num.equals("") && card_num.length() == 16) {
                        CardTyps = sharedPreference.getCardTyps(TApplication.applicationContext);
                        for (CardTypeModel p : CardTyps) {

                            if (p.cardTypNum.equals(card_num.substring(0, 6))) {
//                                imgBankType.setVisibility(View.VISIBLE);
//                                imgBankType.setImageResource(p.cardTypPic);
                                dialogMainBackground.setBackgroundResource(p.cardTypPic);
                            }
                        }
                    }
                }
            }
        });

        dialogBtnConfimation.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @TargetApi(23)
            @Override
            public void onComplete(RippleView rippleView) {
                if (cardNumber.getText().toString().trim().replaceAll("\\-", "").length() == 16) {
                    cardnum = cardNumber.getText().toString().trim().replaceAll("\\-", "");
                    pass = secondPass.getText().toString();
                    date = tarikh.getText().toString();
                    cv2 = cvv2.getText().toString();
                    String encodedHash = Uri.encode("#");
                    CardModel cardModel = new CardModel();
                    if (infoCheckBox.isChecked()) {
                        CardTyps = sharedPreference.getCardTyps(TApplication.applicationContext);
                        for (CardTypeModel p : CardTyps) {

                            if (p.cardTypNum.equals(cardnum.substring(0, 6))) {
//                                imgBankType.setVisibility(View.VISIBLE);
//                                imgBankType.setImageResource(p.cardTypPic);
                                cardModel.cardTypPic = p.cardTypPic;
                            }
                        }
                        cardModel.cardName = edtxtCardName.getText().toString();
                        cardModel.cardNum = cardNumber.getText().toString().trim().replaceAll("\\-", "");
                        cardModel.cardDate = tarikh.getText().toString();
                        cardModel.cardCvv2 = cvv2.getText().toString();
                        TApplication.cardModels.add(cardModel);
                        sharedPreference = new SharedPreference();
                        sharedPreference.addCard(TApplication.applicationContext, cardModel);
                        ArrayList<CardModel> cards = sharedPreference
                                .getCards(TApplication.applicationContext);
                        adapter = new AdapterCardList(cards);
                        lstContent.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    } else if (StaticModel.activityType.equals("CSCA")) {
//                int permissionCheck = ContextCompat.checkSelfPermission(TApplication.currentActivity, Manifest.permission.CALL_PHONE);
                        String callPermission = Manifest.permission.CALL_PHONE;
                        int hasPermission = ContextCompat.checkSelfPermission(TApplication.currentActivity, callPermission);
                        String[] permissions = new String[]{callPermission};
                        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(permissions, 1);
                        } else {
//                        Toast.makeText(TApplication.currentActivity, "We already have persmission", Toast.LENGTH_SHORT).show();
                            ussd = "*765*1*2*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                                    + date + "*" + encodedHash;
                            Ussd.callUssd(ussd);
                        }
                    } else if (StaticModel.activityType.equals("CardToCard")) {
                        showDialog("CardToCard");
                    } else if (StaticModel.activityType.equals("PayBillCard")) {
                        String callPermission = Manifest.permission.CALL_PHONE;
                        int hasPermission = ContextCompat.checkSelfPermission(TApplication.currentActivity, callPermission);
                        String[] permissions = new String[]{callPermission};
                        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(permissions, 3);
                        } else {
//                        Toast.makeText(TApplication.currentActivity, "We already have persmission", Toast.LENGTH_SHORT).show();
                            ussd = "*765*3*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                                    + date + "*" + StaticModel.billId + "*" + StaticModel.billPayID + "*1*" + encodedHash;
                            Ussd.callUssd(ussd);
                        }
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(CardManagmentActivity.this, "شماره کارت نامعتبر می باشد", Toast.LENGTH_LONG).show();
                }

            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        Toast.makeText(TApplication.currentActivity, "CALL_PHONE GRANTED", Toast.LENGTH_SHORT).show();
        String encodedHash = Uri.encode("#");
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ussd = "*765*1*2*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                            + date + "*" + encodedHash;
                    Ussd.callUssd(ussd);
                } else {
                    Toast.makeText(TApplication.currentActivity, "CALL_PHONE DENIED", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ussd = "*765*2*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                            + date + "*" + secondCardNumber + "*" + price + "*" + encodedHash;
                    Ussd.callUssd(ussd);
                } else {
                    Toast.makeText(TApplication.currentActivity, "CALL_PHONE DENIED", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ussd = "*765*3*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                            + date + "*" + StaticModel.billId + "*" + StaticModel.billPayID + "*1*" + encodedHash;
                    Ussd.callUssd(ussd);
                } else {
                    Toast.makeText(TApplication.currentActivity, "CALL_PHONE DENIED", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void showDialog(String type) {
        final Dialog dialog = new Dialog(CardManagmentActivity.this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_card_to_card);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        final DPEditText editCardNumber = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_card_number);
        final DPEditText editPrice = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_price);
        final RippleView btnConfirm = (RippleView) dialog.findViewById(R.id.dialog_entry_info_btn_confirmation);
        final RippleView btnCancel = (RippleView) dialog.findViewById(R.id.dialog_entry_info_btn_cancel);
        editCardNumber.addTextChangedListener(new FourDigitCardFormatWatcher());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(23)
            @Override
            public void onClick(View v) {
                secondCardNumber = editCardNumber.getText().toString().trim().replaceAll("\\-", "");
                price = editPrice.getText().toString().trim();
                String encodedHash = Uri.encode("#");
                String callPermission = Manifest.permission.CALL_PHONE;
                int hasPermission = ContextCompat.checkSelfPermission(TApplication.currentActivity, callPermission);
                String[] permissions = new String[]{callPermission};
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 2);
                } else {
//                        Toast.makeText(TApplication.currentActivity, "We already have persmission", Toast.LENGTH_SHORT).show();
                    ussd = "*765*2*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                            + date + "*" + secondCardNumber + "*" + price + "*" + encodedHash;
                    Ussd.callUssd(ussd);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
