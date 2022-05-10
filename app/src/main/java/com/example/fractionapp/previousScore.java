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
}

