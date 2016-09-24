package com.bank24.hi.hekmatbank.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.bank24.hi.hekmatbank.Model.CardModel;
import com.bank24.hi.hekmatbank.Model.CardTypeModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedPreference {
    public static final String PREFS_NAME1 = "card_preference";
    public static final String PREFS_NAME2 = "card_type_preference";
    public static final String CARDS = "Card";
    public static final String CARDTyps = "Cardtype";


    public SharedPreference() {
        super();
    }

    public void saveCards(Context context, ArrayList<CardModel> cards) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME1, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonCards = gson.toJson(cards);

        editor.putString(CARDS, jsonCards);

        editor.commit();
    }
    public void saveCardTyps(Context context, ArrayList<CardTypeModel> cardTyps) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME2, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonCards = gson.toJson(cardTyps);
        editor.putString(CARDTyps, jsonCards);
        editor.commit();
    }

    public void addCard(Context context, CardModel cardModel) {
        ArrayList<CardModel> cards = getCards(context);
        if (cards == null)
            cards = new ArrayList<CardModel>();
        cards.add(cardModel);
        saveCards(context, cards);
    }
    public void addCardType(Context context, CardTypeModel cardTypeModel) {
        ArrayList<CardTypeModel> cardTyps = getCardTyps(context);
        if (cardTyps == null)
            cardTyps = new ArrayList<CardTypeModel>();
        cardTyps.add(cardTypeModel);
        saveCardTyps(context, cardTyps);
    }

    public ArrayList<CardModel> getCards(Context context) {
        SharedPreferences settings = null;
        ArrayList<CardModel> cards = null;
        try {
            settings = context.getSharedPreferences(PREFS_NAME1,
                    Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonCards = settings.getString(CARDS, null);
        if (jsonCards != null && !jsonCards.isEmpty()) {
            Gson gson = new Gson();
            CardModel[] cardItems = gson.fromJson(jsonCards,
                    CardModel[].class);

            cards = new ArrayList<CardModel>( Arrays.asList(cardItems));
        } else {
            cards = new ArrayList<CardModel>();
        }
        return cards;
    }
    public ArrayList<CardTypeModel> getCardTyps(Context context) {
        SharedPreferences settings = null;
        ArrayList<CardTypeModel> cardTyps = null;
        try {
            settings = context.getSharedPreferences(PREFS_NAME2,
                    Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonCards = settings.getString(CARDTyps, null);
        if (jsonCards != null && !jsonCards.isEmpty()) {
            Gson gson = new Gson();
            CardTypeModel[] cardItems = gson.fromJson(jsonCards,
                    CardTypeModel[].class);

            cardTyps = new ArrayList<CardTypeModel>( Arrays.asList(cardItems));
        } else {
            cardTyps = new ArrayList<CardTypeModel>();
        }
        return cardTyps;
    }

    public void removeCards(Context context, CardModel cardModel) {
        ArrayList<CardModel> cards = getCards(context);
        if (cards != null) {
            cards.remove(cardModel);
            for (int i=0;i<cards.size();i++)
            {
                CardModel oldFm = cards.get(i);
                if(oldFm.cardNum.equals(cardModel.cardNum))
                {
                    cards.remove(i);
                    break;
                }
            }
            saveCards(context, cards);
        }
    }
}
