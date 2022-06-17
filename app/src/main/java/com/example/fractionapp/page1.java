package com.example.fractionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class page1 extends AppCompatActivity{
    previousScore previousScore;
    Button next_btn, direction_btn, dialogBox_cancel_btn;
    TextView addbtn1, increment_level, decrement_level,number, decrement_btn, second, note1, note2, webLink, four_six, seven_nine, ten_twelve, four_twelve;
    int _number;
    static String level = "";
    ViewGroup viewGroup;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    View dialogView;
    int set_Limit = 3;
    String purchase_btn_press = "";
    BillingClient billingClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        try {
            previousScore = new previousScore(getApplicationContext());
            builder = new AlertDialog.Builder(this);
            dialogView = new View(page1.this);
            findViews();
            if (previousScore.getScore().equals("")) {
                note2.setText("High Score 0/10 (");
            } else {
                note2.setText("High Score " + previousScore.getScore() + "/" + "10" + " (");
            }
            if(previousScore.getPurchaseValueFromPref() == true){
                set_Limit = 6;
            }else{}
            if(previousScore.getPurchaseValueFromPref_level_7_9() == true){
                set_Limit = 9;
            }else{}
            if(previousScore.getPurchaseValueFromPref_level_10_12() == true){
                set_Limit = 12;
            }else{}
            if(previousScore.getPurchaseValueFromPref_level_4_12() == true){
                set_Limit = 12;
            }else{}
            _number = Integer.parseInt(number.getText().toString());
            level = note1.getText().toString();
            dialogView = LayoutInflater.from(page1.this).inflate(R.layout.customview, viewGroup, false);
            four_twelve = dialogView.findViewById(R.id.four_twelve);
            ten_twelve = dialogView.findViewById(R.id.ten_twelve);
            seven_nine = dialogView.findViewById(R.id.seven_nine);
            four_six = dialogView.findViewById(R.id.four_six);
            dialogBox_cancel_btn = dialogView.findViewById(R.id.cancel_button_dialogBox);
            actionListners();
            //Initialize a BillingClient with PurchasesUpdatedListener onCreate method
            billingClient = BillingClient.newBuilder(getApplicationContext())
                    .setListener(new PurchasesUpdatedListener() {
                        @Override
                        public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                                for (Purchase purchase : list) {
                                    verifyPayment(purchase);
                                }
                            }

                        }
                    })
                    .enablePendingPurchases()
                    .build();
            // call connectGooglePlayBilling()
            connectGooglePlayBilling();
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    void connectGooglePlayBilling() {

        billingClient.startConnection(new BillingClientStateListener() {

            @Override
            public void onBillingServiceDisconnected() {
                connectGooglePlayBilling();
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Log.d("TAG", "Connected " + 0);
                    getProducts();
                }

            }
        });
    }
    void getProducts() {
        List<String> skuList = new ArrayList<>();
        //replace these with your product IDs from google play console
        skuList.add("level_4_6");
        skuList.add("l_7_9");
        skuList.add("l_10_12");
        skuList.add("l_4_12");
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
       params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        // Process the result.
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                            for (SkuDetails skuDetails : skuDetailsList) {
                                if (skuDetails.getSku().equals("level_4_6")) {
                                        four_six.setOnClickListener(view -> {
                                            launchPurchaseFlow(skuDetails);
                                            purchase_btn_press = "level_4_6";
                                            alertDialog.dismiss();
                                        });
                                } else if (skuDetails.getSku().equals("l_7_9")) {
                                    seven_nine.setOnClickListener(view -> {
                                            launchPurchaseFlow(skuDetails);
                                            purchase_btn_press = "l_7_9";
                                            alertDialog.dismiss();
                                    });
                                } else if (skuDetails.getSku().equals("l_10_12")) {
                                    ten_twelve.setOnClickListener(view -> {
                                            launchPurchaseFlow(skuDetails);
                                            purchase_btn_press = "l_10_12";
                                            alertDialog.dismiss();
                                    });
                                } else if (skuDetails.getSku().equals("l_4_12")) {
                                    four_twelve.setOnClickListener(view -> {
                                            launchPurchaseFlow(skuDetails);
                                            purchase_btn_press = "l_4_12";
                                            alertDialog.dismiss();
                                    });
                                }else{}
                            }
                        }

                    }
                });
    }
    void launchPurchaseFlow(SkuDetails skuDetails) {

        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build();

        billingClient.launchBillingFlow(page1.this, billingFlowParams);
    }
    void verifyPayment(Purchase purchase) {
        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .build();

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    if (purchase.getSkus().get(0).equals("level_4_6")) {
                        previousScore.savePurchaseValueToPref(true);
                        Intent intent = new Intent(page1.this,page1.class);
                        startActivity(intent);
                        finish();
                    } else if (purchase.getSkus().get(0).equals("l_7_9")) {
                        previousScore.savePurchaseValueToPref_level_7_9(true);
                        Intent intent = new Intent(page1.this,page1.class);
                        startActivity(intent);
                        finish();
                    } else if (purchase.getSkus().get(0).equals("l_10_12")) {
                        previousScore.savePurchaseValueToPref_level_10_12(true);
                        Intent intent = new Intent(page1.this,page1.class);
                        startActivity(intent);
                        finish();
                    }else if (purchase.getSkus().get(0).equals("l_4_12")) {
                        previousScore.savePurchaseValueToPref_level_4_12(true);
                        Intent intent = new Intent(page1.this,page1.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        };
        billingClient.consumeAsync(consumeParams, listener);
    }

    protected void onResume() {
        try {
            super.onResume();
            billingClient.queryPurchasesAsync(
                    BillingClient.SkuType.INAPP,
                    new PurchasesResponseListener() {
                        @Override
                        public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> list) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                for (Purchase purchase : list) {
                                    if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                                        verifyPayment(purchase);
                                    }
                                }
                            }
                        }
                    }
            );
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ((ViewGroup)dialogView.getParent()).removeView(dialogView);
            }
        });
        webLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLink.setTextColor(Color.GREEN);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.MonumentalMath.com"));
                startActivity(browserIntent);
                webLink.setTextColor(Color.parseColor("#006AFF"));
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
        dialogBox_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
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
                            builder.setView(dialogView);
                            alertDialog = builder.create();
                            alertDialog.show();
                            if(_number == 12){
                                alertDialog.dismiss();
                            }
                        }
                        else if(_number == 12){
                            alertDialog.dismiss();
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