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
}

