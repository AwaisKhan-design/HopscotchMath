package com.example.fractionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class previousScore {
    private SharedPreferences sharedPreferences;
public previousScore(Context context){
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
}
public void savesession(String score){
    sharedPreferences.edit().putString("previous_score",score).apply();
}
public String getScore(){
    return sharedPreferences.getString("previous_score","");
}
    public void savetime(String time){
        sharedPreferences.edit().putString("previous_time",time).apply();
    }
    public String getsavetime(){
        return sharedPreferences.getString("previous_time","");
    }

    public void savecartoon(String cartoon){
        sharedPreferences.edit().putString("cartoon",cartoon).apply();
    }
    public String getcartoon(){
        return sharedPreferences.getString("cartoon","");
    }
    public void saveset(String set){
        sharedPreferences.edit().putString("set",set).apply();
    }
    public String getset(){
        return sharedPreferences.getString("set","");
    }
    public void saveLevel(String level){
        sharedPreferences.edit().putString("Level",level).apply();
    }
    public String getLevel(){
        return sharedPreferences.getString("Level","");
    }
    protected void savePurchaseValueToPref ( Boolean value){
        sharedPreferences.edit().putBoolean("PURCHASE_Level_4_6", value).commit();
    }
        protected Boolean getPurchaseValueFromPref () {
        return sharedPreferences.getBoolean("PURCHASE_Level_4_6", false);
    }
    protected void savePurchaseValueToPref_level_7_9 ( Boolean value){
        sharedPreferences.edit().putBoolean("PURCHASE_Level_7_9", value).commit();
    }
    protected Boolean getPurchaseValueFromPref_level_7_9 () {
        return sharedPreferences.getBoolean("PURCHASE_Level_7_9", false);
    }
    protected void savePurchaseValueToPref_level_10_12 ( Boolean value){
        sharedPreferences.edit().putBoolean("PURCHASE_Level_10_12", value).commit();
    }
    protected Boolean getPurchaseValueFromPref_level_10_12 () {
        return sharedPreferences.getBoolean("PURCHASE_Level_10_12", false);
    }
    protected void savePurchaseValueToPref_level_4_12 ( Boolean value){
        sharedPreferences.edit().putBoolean("PURCHASE_Level_4_12", value).commit();
    }
    protected Boolean getPurchaseValueFromPref_level_4_12 () {
        return sharedPreferences.getBoolean("PURCHASE_Level_4_12", false);
    }
}

