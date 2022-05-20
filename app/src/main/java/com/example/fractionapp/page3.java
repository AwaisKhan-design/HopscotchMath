package com.example.fractionapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class page3 extends AppCompatActivity {
Button back_button;
String level, set_level;
ImageView cartoon1, cartoon2, cartoon3, cartoon4, cartoon5;
AnimationDrawable animation;
previousScore previousScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        previousScore = new previousScore(this);
        animation = new AnimationDrawable();
        findView();
        Intent intent = getIntent();
       level = intent.getStringExtra("level");
       set_level = intent.getStringExtra("set_level");
        difficulty();
        action_Listeners();
    }
    private void setRedImage() {
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00099),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00100),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00101),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00102),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00103),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00104),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00105),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_red_00106),50);
        animation.setOneShot(true);
        cartoon1.setImageDrawable(animation);
        animation.start();
    }
    private void setBlueImage() {
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00099),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00100),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00101),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00102),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00103),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00104),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00105),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_blue_00106),50);
        animation.setOneShot(true);
        cartoon2.setImageDrawable(animation);
        animation.start();
    }
    private void setGreenImage() {
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00099),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00100),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00101),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00102),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00103),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00104),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00105),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_green_00106),50);
        animation.setOneShot(true);
        cartoon3.setImageDrawable(animation);
        animation.start();
    }
    private void setPinkImage() {
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00099),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00100),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00101),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00102),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00103),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00104),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00105),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_pink_00106),50);
        animation.setOneShot(true);
        cartoon4.setImageDrawable(animation);
        animation.start();
    }
    private void setBlackImage() {
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00099),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00100),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00101),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00102),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00103),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00104),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00105),50);
        animation.addFrame(getResources().getDrawable(R.drawable.jump_black_00106),50);
        animation.setOneShot(true);
        cartoon5.setImageDrawable(animation);
        animation.start();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(page3.this, page1.class);
        startActivity(intent);
        finish();
    }

    private void findView() {
        back_button = findViewById(R.id.back_button);
        cartoon1 = findViewById(R.id.carton1);
        cartoon2 = findViewById(R.id.cartoon2);
        cartoon3 = findViewById(R.id.cartoon3);
        cartoon4 = findViewById(R.id.cartoon4);
        cartoon5 = findViewById(R.id.cartoon5);
    }
    private void action_Listeners() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page3.this, page1.class);
                startActivity(intent);
                finish();
            }
        });
        cartoon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRedImage();
                cartoon1.setClickable(false);
                cartoon2.setClickable(false);
                cartoon3.setClickable(false);
                cartoon4.setClickable(false);
                cartoon5.setClickable(false);
                back_button.setClickable(false);
                previousScore.saveset(set_level);
                previousScore.savecartoon("1");
                previousScore.saveLevel(level);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(page3.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3500);
            }
        });
        cartoon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBlueImage();
                cartoon1.setClickable(false);
                cartoon2.setClickable(false);
                cartoon3.setClickable(false);
                cartoon4.setClickable(false);
                cartoon5.setClickable(false);
                back_button.setClickable(false);
                previousScore.saveset(set_level);
                previousScore.savecartoon("2");
                previousScore.saveLevel(level);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(page3.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3500);
            }
        });
        cartoon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGreenImage();
                cartoon1.setClickable(false);
                cartoon2.setClickable(false);
                cartoon3.setClickable(false);
                cartoon4.setClickable(false);
                cartoon5.setClickable(false);
                back_button.setClickable(false);
                previousScore.saveset(set_level);
                previousScore.savecartoon("3");
                previousScore.saveLevel(level);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(page3.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3500);
            }
        });
        cartoon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPinkImage();
                cartoon1.setClickable(false);
                cartoon2.setClickable(false);
                cartoon3.setClickable(false);
                cartoon4.setClickable(false);
                cartoon5.setClickable(false);
                back_button.setClickable(false);
                previousScore.saveset(set_level);
                previousScore.savecartoon("4");
                previousScore.saveLevel(level);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(page3.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3500);
            }
        });
        cartoon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBlackImage();
                cartoon1.setClickable(false);
                cartoon2.setClickable(false);
                cartoon3.setClickable(false);
                cartoon4.setClickable(false);
                cartoon5.setClickable(false);
                back_button.setClickable(false);
                previousScore.saveset(set_level);
                previousScore.savecartoon("5");
                previousScore.saveLevel(level);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(page3.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3500);
            }
        });
    }
    private void difficulty(){
        if(level.equals("Novice")){
            level = "95";
        }else if(level.equals("Confident")){
            level = "90";
        }else{
            level = "60";
        }
    }
}