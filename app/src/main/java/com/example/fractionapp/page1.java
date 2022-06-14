package com.example.fractionapp;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class page1 extends AppCompatActivity implements PurchasesUpdatedListener {
    previousScore previousScore;
    Button next_btn, direction_btn, dialogBox_cancel_btn;
    TextView addbtn1, increment_level, decrement_level,number, decrement_btn, second, note1, note2, webLink, four_six, seven_nine, ten_twelve, four_twelve;
    int _number;
    static String level = "";
    ViewGroup viewGroup;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    View dialogView;
    private BillingClient billingClient;
    int set_Limit = 3;
    public static String PRODUCT_ID= "";
    String purchase_btn_press = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        try {
            previousScore = new previousScore(getApplicationContext());
            builder = new AlertDialog.Builder(page1.this);
            findViews();
            if (previousScore.getScore().equals("")) {
                note2.setText("High Score 0/10 (");
            } else {
                note2.setText("High Score " + previousScore.getScore() + "/" + "10" + " (");
            }
            _number = Integer.parseInt(number.getText().toString());
            level = note1.getText().toString();
            actionListners();
            billingClient = BillingClient.newBuilder(this)
                    .enablePendingPurchases().setListener(this).build();
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        Purchase.PurchasesResult queryPurchase = billingClient.queryPurchases(INAPP);
                        List<Purchase> queryPurchases = queryPurchase.getPurchasesList();
                        if (queryPurchases != null && queryPurchases.size() > 0) {
                            handlePurchases(queryPurchases);
                        }
                        //if purchase list is empty that means item is not purchased
                        //Or purchase is refunded or canceled
                        else {
                            if(purchase_btn_press.equals("level_4_6")){
                                previousScore.savePurchaseValueToPref(false);
                            }else if(purchase_btn_press.equals("l_7_9")){
                                previousScore.savePurchaseValueToPref_level_7_9(false);
                            }else if(purchase_btn_press.equals("l_10_12")){
                                previousScore.savePurchaseValueToPref_level_10_12(false);
                            }else if(purchase_btn_press.equals("l_4_12")){
                                previousScore.savePurchaseValueToPref_level_4_12(false);
                            }
                        }
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                }
            });
            //item Purchased OR Item Not Purchased
            if (previousScore.getPurchaseValueFromPref() == true) {
                set_Limit = 6;
            }
            else {}
            if(previousScore.getPurchaseValueFromPref_level_7_9() == true){
                set_Limit = 9;
            }
            else {}
            if(previousScore.getPurchaseValueFromPref_level_10_12() == true){
                set_Limit = 12;
            }
            else {}
            if(previousScore.getPurchaseValueFromPref_level_4_12() == true){
                set_Limit = 12;
            }
            else {}
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //initiate purchase on button click
    public void purchase () {
        //check if service is already connected
        if (billingClient.isReady()) {
            initiatePurchase();
        }
        //else reconnect service
        else {
            billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        initiatePurchase();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                }
            });
        }
    }
    private void initiatePurchase () {
        List<String> skuList = new ArrayList<>();
        skuList.add(PRODUCT_ID);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            if (skuDetailsList != null && skuDetailsList.size() > 0) {
                                BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(skuDetailsList.get(0))
                                        .build();
                                billingClient.launchBillingFlow(page1.this, flowParams);
                            } else {
                                //try to add item/product id "purchase" inside managed product in google play console
                                Toast.makeText(getApplicationContext(), "Purchase Item not Found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    " Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onPurchasesUpdated (BillingResult
                                            billingResult, @Nullable List < Purchase > purchases){
        //if item newly purchased
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            handlePurchases(purchases);
        }
        //if item already purchased then check and reflect changes
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            Purchase.PurchasesResult queryAlreadyPurchasesResult = billingClient.queryPurchases(INAPP);
            List<Purchase> alreadyPurchases = queryAlreadyPurchasesResult.getPurchasesList();
            if (alreadyPurchases != null) {
                handlePurchases(alreadyPurchases);
            }
        }
        //if purchase cancelled
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(getApplicationContext(), "Purchase Cancelled", Toast.LENGTH_SHORT).show();
        }
        // Handle any other error msgs
        else {
            Toast.makeText(getApplicationContext(), "Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    void handlePurchases (List < Purchase > purchases) {
        for (Purchase purchase : purchases) {
            //if item is purchased
            if (PRODUCT_ID.equals(purchase.getSku()) && purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
                    // Invalid purchase
                    // show error to user
                    Toast.makeText(getApplicationContext(), "Error : Invalid Purchase", Toast.LENGTH_SHORT).show();
                    return;
                }
                // else purchase is valid
                //if item is purchased and not acknowledged
                if (!purchase.isAcknowledged()) {
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, ackPurchase);
                }
                //else item is purchased and also acknowledged
                else {
                    // Grant entitlement to the user on item purchase
                    // restart activity
                    if (!previousScore.getPurchaseValueFromPref()) {
                        if(purchase_btn_press.equals("level_4_6")){
                            previousScore.savePurchaseValueToPref(true);
                            Intent intent = new Intent(page1.this,page1.class);
                            startActivity(intent);
                            finish();
                        }else{}
                    }else{}
                    if (!previousScore.getPurchaseValueFromPref_level_7_9()){
                        if(purchase_btn_press.equals("l_7_9")){
                            previousScore.savePurchaseValueToPref_level_7_9(true);
                            Intent intent = new Intent(page1.this,page1.class);
                            startActivity(intent);
                            finish();
                        }else{}
                    }else{}
                    if (!previousScore.getPurchaseValueFromPref_level_10_12()){
                        if(purchase_btn_press.equals("l_10_12")){
                            previousScore.savePurchaseValueToPref_level_10_12(true);
                            Intent intent = new Intent(page1.this,page1.class);
                            startActivity(intent);
                            finish();
                        }else{}
                    }else{}
                    if (!previousScore.getPurchaseValueFromPref_level_4_12()){
                        if(purchase_btn_press.equals("l_4_12")){
                            previousScore.savePurchaseValueToPref_level_4_12(true);
                            Intent intent = new Intent(page1.this,page1.class);
                            startActivity(intent);
                            finish();
                        }else{}
                    }else{}
                }
            }
            //if purchase is pending
            else if (PRODUCT_ID.equals(purchase.getSku()) && purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
                Toast.makeText(getApplicationContext(),
                        "Purchase is Pending. Please complete Transaction", Toast.LENGTH_SHORT).show();
            }
            //if purchase is unknown
            else if (PRODUCT_ID.equals(purchase.getSku()) && purchase.getPurchaseState() == Purchase.PurchaseState.UNSPECIFIED_STATE) {
                if(purchase_btn_press.equals("level_4_6")){
                    previousScore.savePurchaseValueToPref(false);
                }else if(purchase_btn_press.equals("l_7_9")){
                    previousScore.savePurchaseValueToPref_level_7_9(false);
                }else if(purchase_btn_press.equals("l_10_12")){
                    previousScore.savePurchaseValueToPref_level_10_12(false);
                }else if(purchase_btn_press.equals("l_4_12")){
                    previousScore.savePurchaseValueToPref_level_4_12(false);
                }
                Toast.makeText(this, "Purchase Status : Not Purchased", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Purchase Status Unknown", Toast.LENGTH_SHORT).show();
            }
        }
    }
    AcknowledgePurchaseResponseListener ackPurchase = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                //if purchase is acknowledged
                // Grant entitlement to the user. and restart activity
                if(purchase_btn_press.equals("level_4_6")){
                    previousScore.savePurchaseValueToPref(true);
                }else if(purchase_btn_press.equals("l_7_9")){
                    previousScore.savePurchaseValueToPref_level_7_9(true);
                }else if(purchase_btn_press.equals("l_10_12")){
                    previousScore.savePurchaseValueToPref_level_10_12(true);
                }else if(purchase_btn_press.equals("l_4_12")){
                    previousScore.savePurchaseValueToPref_level_4_12(true);
                }
                Toast.makeText(getApplicationContext(), "Item Purchased", Toast.LENGTH_SHORT).show();
                page1.this.recreate();
            }
        }
    };

    /**
     * Verifies that the purchase was signed correctly for this developer's public key.
     * <p>Note: It's strongly recommended to perform such check on your backend since hackers can
     * replace this method with "constant true" if they decompile/rebuild your app.
     * </p>
     */
    private boolean verifyValidSignature (String signedData, String signature){
        try {
            String base64Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg0voCSR1bIFirULG+MBeGE2rq2wlWgIHOkTria2m+NUR2ru22hg+Qcf28cObh8c166/meT2FdH4bC8jB47uo/bGBH2WcgfI1nj3uemfONx22NpjpQ/mbvuVOzPs2G+/Cjj1X2vdVYV8jYIGrs8R1hHLGeUf9ApeIP6WBOETwSyRj81WKjQQnl8cZ/W5auc8Kh/6e5mWrIbsyzvSwk78weF3EXT9OnmkOLlsjMK4MH7flSnwB0xqjYXrxo3tx2F3oyXZ5THMLAxa9wO8NwJjYss1irO2zqt45jzWsqMWRnImG7qtYqxLksz05QlSqa4R+uYTbvQYb3gRFgHbZbRqKfwIDAQAB";
            return Security.verifyPurchase(base64Key, signedData, signature);
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
        if (billingClient != null) {
            billingClient.endConnection();
        }
    }
    private void findViews() {
        next_btn = findViewById(R.id.btn_next);
        direction_btn = findViewById(R.id.btn_detail);
        addbtn1 = findViewById(R.id.add_sign);
        decrement_btn = findViewById(R.id.decrement);
        increment_level = findViewById(R.id.increment_level);
        decrement_level = findViewById(R.id.decrement_level);
        note1 = findViewById(R.id.note1);
        number = findViewById(R.id.no_);
        second = findViewById(R.id.second);
        note2 = findViewById(R.id.note2);
        webLink = findViewById(R.id.weblink);
        viewGroup = findViewById(android.R.id.content);
    }
    private void actionListners() {
        webLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.MonumentalMath.com"));
                startActivity(browserIntent);
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(page1.this, page3.class);
                i.putExtra("level", level);
                i.putExtra("set_level", String.valueOf(_number));
                startActivity(i);
                finish();
            }
        });
        direction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(page1.this, page2.class);
                startActivity(i);
                finish();
            }
        });
        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(_number < set_Limit) {
                        String num = String.valueOf(++_number);
                        number.setText(num);
                        decrement_btn.setVisibility(View.VISIBLE);
                        if(_number == set_Limit) {
                            addbtn1.setVisibility(View.INVISIBLE);
                            dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customview, viewGroup, false);
                            four_twelve = dialogView.findViewById(R.id.four_twelve);
                            ten_twelve = dialogView.findViewById(R.id.ten_twelve);
                            seven_nine = dialogView.findViewById(R.id.seven_nine);
                            four_six = dialogView.findViewById(R.id.four_six);
                           dialogBox_cancel_btn = dialogView.findViewById(R.id.cancel_button_dialogBox);
                               four_six.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       if(previousScore.getPurchaseValueFromPref() == true){
                                           Toast.makeText(page1.this, "Already Purchased", Toast.LENGTH_SHORT).show();
                                       }else{
                                       four_six.setBackgroundColor(Color.parseColor("#2F6666"));
                                       purchase_btn_press = "level_4_6";
                                       PRODUCT_ID = "level_4_6";
                                       purchase();
                                       alertDialog.dismiss();
                                   }}
                               });
                                seven_nine.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(previousScore.getPurchaseValueFromPref_level_7_9() == true){
                                            Toast.makeText(page1.this, "Already Purchased", Toast.LENGTH_SHORT).show();
                                        }else{
                                        purchase_btn_press = "l_7_9";
                                        PRODUCT_ID = "l_7_9";
                                        purchase();
                                        alertDialog.dismiss();
                                    }}
                                });
                                ten_twelve.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(previousScore.getPurchaseValueFromPref_level_10_12() == true){
                                            Toast.makeText(page1.this, "Already Purchased", Toast.LENGTH_SHORT).show();
                                        }else {
                                        purchase_btn_press = "l_10_12";
                                        PRODUCT_ID = "l_10_12";
                                        purchase();
                                        alertDialog.dismiss();
                                    }}
                                });
                                four_twelve.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(previousScore.getPurchaseValueFromPref_level_4_12() == true){
                                            Toast.makeText(page1.this, "Already Purchased", Toast.LENGTH_SHORT).show();
                                        }else {
                                        purchase_btn_press = "l_4_12";
                                        PRODUCT_ID = "l_4_12";
                                        purchase();
                                        alertDialog.dismiss();
                                    }}
                                });
                            dialogBox_cancel_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    alertDialog.cancel();
                            }
        });
                            builder.setView(dialogView);
                            alertDialog = builder.create();
                            alertDialog.show();
                            if(_number == 12){
                                alertDialog.dismiss();
                            }
                        }
                        else if(_number == 12){
                            alertDialog.cancel();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(page1.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        decrement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (_number <= set_Limit || _number > 0) {
                        String num = String.valueOf(--_number);
                        number.setText(num);
                        if(_number == 1){
                            decrement_btn.setVisibility(View.INVISIBLE);
                        }else if(_number == set_Limit){
                            builder.setView(dialogView);
                            alertDialog = builder.create();
                            alertDialog.show();
                            if(_number == 12){
                                alertDialog.dismiss();
                            }
                        }else if(_number < set_Limit) {
                            addbtn1.setVisibility(View.VISIBLE);
                        }
                    }
                    else{}
                }catch (Exception e){
                    Toast.makeText(page1.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        increment_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    switch (level){
                        case "Novice":
                            second.setText("90");
                            note1.setText("Confident");
                            level = note1.getText().toString();
                            decrement_level.setVisibility(View.VISIBLE);
                            break;
                        case "Confident":
                            second.setText("60");
                            note1.setText("Maniac");
                            level = note1.getText().toString();
                            increment_level.setVisibility(View.INVISIBLE);
                            break;
                    }
                }catch (Exception e){
                    Toast.makeText(page1.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        decrement_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    switch (level){
                        case "Maniac":
                            second.setText("90");
                            note1.setText("Confident");
                            level = note1.getText().toString();
                            decrement_level.setVisibility(View.VISIBLE);
                            increment_level.setVisibility(View.VISIBLE);
                            break;
                        case "Confident":
                            second.setText("120");
                            note1.setText("Novice");
                            level = note1.getText().toString();
                            increment_level.setVisibility(View.VISIBLE);
                            decrement_level.setVisibility(View.INVISIBLE);
                            break;
                    }
                }catch (Exception e){
                    Toast.makeText(page1.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}