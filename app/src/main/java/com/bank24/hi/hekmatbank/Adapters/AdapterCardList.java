package com.bank24.hi.hekmatbank.Adapters;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bank24.hi.hekmatbank.Model.CardModel;
import com.bank24.hi.hekmatbank.Model.CardTypeModel;
import com.bank24.hi.hekmatbank.Model.StaticModel;
import com.bank24.hi.hekmatbank.R;
import com.bank24.hi.hekmatbank.TApplication;
import com.bank24.hi.hekmatbank.Utility.FourDigitCardFormatWatcher;
import com.bank24.hi.hekmatbank.Utility.SharedPreference;
import com.bank24.hi.hekmatbank.Utility.Ussd;
import com.bank24.hi.hekmatbank.ui.DPEditText;
import com.bank24.hi.hekmatbank.ui.rippleView.RippleView;

import java.util.ArrayList;

/**
 * Created by a.ebrahiminia on 8/1/2016.
 */
public class AdapterCardList extends ArrayAdapter<CardModel> {


    public AdapterCardList(ArrayList<CardModel> array) {
        super(TApplication.applicationContext, R.layout.activity_card_managment_lst_items, array);
    }

    private static class ViewHolder {

        public TextView cardTxtName;
        public TextView cardTxtNum;
        public ImageView imgTrash;
        public ImageView imageView15;
        SharedPreference sharedPreference = new SharedPreference();
        //        public TextView  txtPhone;
//        public ImageView imgDelete;
        public ViewGroup cardlayoutRoot;
        String cardnum;
        String cv2;
        String date;
        String pass;
        String secondCardNumber;
        String price;

        public ViewHolder(View view) {
            cardTxtName = (TextView) view.findViewById(R.id.card_managment_txt_bank_name);
            cardTxtNum = (TextView) view.findViewById(R.id.card_managment_txt_card_num);
            imgTrash = (ImageView) view.findViewById(R.id.trash);
            imageView15 = (ImageView) view.findViewById(R.id.imageView15);
//            txtPhone= (TextView) view.findViewById(R.id.favorite_num);
            cardlayoutRoot = (ViewGroup) view.findViewById(R.id.card_list_items_lay);
//            imgDelete = (ImageView) view.findViewById(R.id.delete_pic);
        }


        public void fill(final ArrayAdapter<CardModel> adapter, final CardModel item, final int position) {
            cardTxtName.setText(item.cardName);
            cardTxtNum.setText("**** " + item.cardNum.substring(12, 16));
            if(item.cardTypPic != null){
                imageView15.setImageResource(item.cardTypPic);
            }
//            txtPhone.setText(item.favorite_num);
//            chkDone.setChecked(item.done);

//            imgDelete.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    adapter.remove(item);
//                    SharedPreference sharedPreference = new SharedPreference();
//                    sharedPreference.removeCards(TApplication.applicationContext, item);
//
//                }
//            });
            cardlayoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (!StaticModel.activityType.equals("CMA")) {
                        final Dialog dialog = new Dialog(TApplication.currentActivity, R.style.NewDialog);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_entry_wallet_password);
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(true);
                        RippleView txtOk = (RippleView) dialog.findViewById(R.id.dialog_entry_info_btn_confirmation);
                        final DPEditText secondPassTxt = (DPEditText) dialog.findViewById(R.id.dialog_entry_info_edit_wallet_password);
                        txtOk.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                            @TargetApi(23)
                            @Override
                            public void onComplete(RippleView rippleView) {
                                cardnum = item.cardNum;
                                cv2 = item.cardCvv2;
                                date = item.cardDate;
                                pass = secondPassTxt.getText().toString();
                                String encodedHash = Uri.encode("#");
                                String ussd = "";
                                String callPermission = Manifest.permission.CALL_PHONE;
                                int hasPermission = ContextCompat.checkSelfPermission(TApplication.currentActivity, callPermission);
                                String[] permissions = new String[]{
                                        callPermission
                                };
                                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                                    if(StaticModel.activityType.equals("CSCA")){
                                        TApplication.currentActivity.requestPermissions(permissions, 1);
                                    }else if(StaticModel.activityType.equals("CardToCard")){
                                        showDialog("");
                                    }else if(StaticModel.activityType.equals("PayBillCard")){
                                        TApplication.currentActivity.requestPermissions(permissions, 3);
                                    }

                                } else if (StaticModel.activityType.equals("CSCA")) {
//                                    Toast.makeText(TApplication.currentActivity, "We already have persmission", Toast.LENGTH_SHORT).show();

                                    ussd = "*765*1*2*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                                            + date + "*" + encodedHash;
                                    Ussd.callUssd(ussd);
                                } else if (StaticModel.activityType.equals("CardToCard")) {
                                    showDialog("");
                                } else if (StaticModel.activityType.equals("PayBillCard")) {
                                    ussd = "*765*3*1*" + cardnum + "*" + pass + "*" + cv2 + "*"
                                            + date + "*" + StaticModel.billId + "*" + StaticModel.billPayID + "*1*" + encodedHash;
                                    Ussd.callUssd(ussd);
                                }
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    } else {

                    }
                }
            });
            imgTrash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.remove(item);
                    SharedPreference sharedPreference = new SharedPreference();
                    sharedPreference.removeCards(TApplication.applicationContext, item);
                }
            });
        }

        private void showDialog(String type) {
            final Dialog dialog = new Dialog(TApplication.currentActivity, R.style.NewDialog);
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
                    String ussd;
                    String callPermission = Manifest.permission.CALL_PHONE;
                    int hasPermission = ContextCompat.checkSelfPermission(TApplication.currentActivity, callPermission);
                    String[] permissions = new String[]{callPermission};
                    if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                        TApplication.currentActivity.requestPermissions(permissions, 2);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        CardModel item = getItem(position);
        if (convertView == null) {
            convertView = TApplication.inflater.inflate(R.layout.activity_card_managment_lst_items, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.fill(this, item, position);
        return convertView;
    }
}
