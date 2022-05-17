package com.example.fractionapp;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button quit;
TextView counter, text1,text2,text3,text4,text5,text6,text7,text8,question_nominator,question_denominator,attempted_qts;
ImageView cartoon_place, counting_Image;
LinearLayout textBox1,textBox2,textBox3,textBox4;
RelativeLayout ll;
CountDownTimer countDownTimer;
int count = 0,height;
static int cart = 0;
AnimationDrawable animation;
String set_level,_text1,_text2,_text3,_text4;
private ArrayList<QuizModel> quizModelarrayList;
Random random;
int currentScore = 0, questionAttempted = 1, currentpos,finishTime;
    View viewPager;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Intent intent = getIntent();
            cart = Integer.parseInt(intent.getStringExtra("cartoon"));
            count = Integer.parseInt(intent.getStringExtra("count"));
            finishTime = count;
            set_level = intent.getStringExtra("set_level");
            animation = new AnimationDrawable();
            findViews();
            setCartoon();
            actionListeners();
            reverseTimer();
            quizModelarrayList = new ArrayList<>();
            ll = new RelativeLayout(this);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            height = displayMetrics.heightPixels;
            setLevels();
            random = new Random();
            currentpos = random.nextInt(quizModelarrayList.size());
            setDataView(currentpos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setDataView(int currentpos) {
       try {
           if(questionAttempted == 11){
               final Handler handler = new Handler(Looper.getMainLooper());
               finishTime = finishTime - count;
               countDownTimer.cancel();
               quit.setClickable(false);
               textBox1.setClickable(false);
               textBox2.setClickable(false);
               textBox3.setClickable(false);
               textBox4.setClickable(false);
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent intent = new Intent(MainActivity.this,page4.class);
                       intent.putExtra("score", currentScore);
                       intent.putExtra("countTime",finishTime);
                       startActivity(intent);
                       finish();
                   }
               }, 3500);
           }else {
               question_nominator.setText(quizModelarrayList.get(currentpos).getQuestion_nominator());
               question_denominator.setText(quizModelarrayList.get(currentpos).getQuestion_denominator());
               text1.setText(quizModelarrayList.get(currentpos).getOption1_text1());
               text2.setText(quizModelarrayList.get(currentpos).getOption1_text2());
               text3.setText(quizModelarrayList.get(currentpos).getOption2_text1());
               text4.setText(quizModelarrayList.get(currentpos).getOption2_text2());
               text5.setText(quizModelarrayList.get(currentpos).getOption3_text1());
               text6.setText(quizModelarrayList.get(currentpos).getOption3_text2());
               text7.setText(quizModelarrayList.get(currentpos).getOption4_text1());
               text8.setText(quizModelarrayList.get(currentpos).getOption4_text2());
           }
       }catch (Exception e){
           Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
       }
    }
    private void setLevels() {
        if(set_level.equals("1")){
            setOne(quizModelarrayList);
        }
        else if(set_level.equals("2")){
            setTwo(quizModelarrayList);
        }
        else if(set_level.equals("3")){
            setThree(quizModelarrayList);
        }
        else if(set_level.equals("4")){
            setFour(quizModelarrayList);
        }
        else if(set_level.equals("5")){
            setFive(quizModelarrayList);
        }
        else if(set_level.equals("6")){
            setSix(quizModelarrayList);
        }
        else if(set_level.equals("7")){
            setSeven(quizModelarrayList);
        }
        else if(set_level.equals("8")){
            setEight(quizModelarrayList);
        }
        else if(set_level.equals("9")){
            setNine(quizModelarrayList);
        }
        else if(set_level.equals("10")){
            setTen(quizModelarrayList);
        }
    }
    private void setCartoon() {
        if(cart == 1){
            cartoon_place.setImageResource(R.drawable.dance_red_00021);
        }
        else if(cart == 2){
            cartoon_place.setImageResource(R.drawable.bluebean_h150);
        }
        else if(cart == 3){
            cartoon_place.setImageResource(R.drawable.greenbean_h150);
        }
        else if(cart == 4){
            cartoon_place.setImageResource(R.drawable.pinkbean_h150);
        }
        else{
            cartoon_place.setImageResource(R.drawable.blackbean_h150);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        Intent intent = new Intent(MainActivity.this, page3.class);
        startActivity(intent);
        finish();
    }
    private void findViews() {
        quit = findViewById(R.id.quit_btn);
        counter = findViewById(R.id.counter);
        cartoon_place = findViewById(R.id.cartoon_place);
        question_nominator = findViewById(R.id.question_nominator);
        question_denominator = findViewById(R.id.question_denominator);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        textBox1 = findViewById(R.id.textBox1);
        textBox2 = findViewById(R.id.textBox2);
        textBox3 = findViewById(R.id.textBox3);
        textBox4 = findViewById(R.id.textBox4);
        attempted_qts = findViewById(R.id.attempted_qts);
        counting_Image = findViewById(R.id.countingImage);
        viewPager = findViewById(R.id.pager);
    }
    private void actionListeners() {
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent intent = new Intent(MainActivity.this, page1.class);
                startActivity(intent);
                finish();
            }
        });
        textBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit.setClickable(false);
                textBox1.setClickable(false);
                textBox2.setClickable(false);
                textBox3.setClickable(false);
                textBox4.setClickable(false);
                final Handler handler = new Handler(Looper.getMainLooper());
               _text1 = text1.getText().toString().trim() + text2.getText().toString().trim();
                if(quizModelarrayList.get(currentpos).getAnswer().trim().equals(_text1)){
                    currentScore++;
                    setCartoonImage();
                        setCartoonRightAnimation();
                        textBox1.setBackground(getDrawable(R.drawable.right_answer_shape));
                }else{
                    setCartoonWrongAnimation();
                    textBox1.setBackground(getDrawable(R.drawable.wrong_answer_shape));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textBox1.setBackground(getDrawable(R.drawable.square_shape));
                        attempted_qts.setText(questionAttempted+"/"+"10");
                        questionAttempted++;
                        currentpos = random.nextInt(quizModelarrayList.size());
                        setDataView(currentpos);
                        quit.setClickable(true);
                        textBox1.setClickable(true);
                        textBox2.setClickable(true);
                        textBox3.setClickable(true);
                        textBox4.setClickable(true);
                    }
                }, 3000);
            }
        });
        textBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler(Looper.getMainLooper());
                quit.setClickable(false);
                textBox1.setClickable(false);
                textBox2.setClickable(false);
                textBox3.setClickable(false);
                textBox4.setClickable(false);
                _text2 = text3.getText().toString().trim() + text4.getText().toString().trim();
                if(quizModelarrayList.get(currentpos).getAnswer().trim().equals(_text2)){
                    currentScore++;
                    setCartoonImage();
                    setCartoonRightAnimation();
                    textBox2.setBackground(getDrawable(R.drawable.right_answer_shape));
                }else{
                    setCartoonWrongAnimation();
                    textBox2.setBackground(getDrawable(R.drawable.wrong_answer_shape));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textBox2.setBackground(getDrawable(R.drawable.square_shape));
                        attempted_qts.setText(questionAttempted+"/"+"10");
                        questionAttempted++;
                        currentpos = random.nextInt(quizModelarrayList.size());
                        setDataView(currentpos);
                        quit.setClickable(true);
                        textBox1.setClickable(true);
                        textBox2.setClickable(true);
                        textBox3.setClickable(true);
                        textBox4.setClickable(true);
                    }
                }, 3000);
            }
        });
        textBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler(Looper.getMainLooper());
                quit.setClickable(false);
                textBox1.setClickable(false);
                textBox2.setClickable(false);
                textBox3.setClickable(false);
                textBox4.setClickable(false);
                _text3 = text5.getText().toString().trim() + text6.getText().toString().trim();
                if(quizModelarrayList.get(currentpos).getAnswer().trim().equals(_text3)){
                    currentScore++;
                    setCartoonImage();
                    setCartoonRightAnimation();
                    textBox3.setBackground(getDrawable(R.drawable.right_answer_shape));
                }else{
                    setCartoonWrongAnimation();
                    textBox3.setBackground(getDrawable(R.drawable.wrong_answer_shape));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textBox3.setBackground(getDrawable(R.drawable.square_shape));
                        attempted_qts.setText(questionAttempted+"/"+"10");
                        questionAttempted++;
                        currentpos = random.nextInt(quizModelarrayList.size());
                        setDataView(currentpos);
                        quit.setClickable(true);
                        textBox1.setClickable(true);
                        textBox2.setClickable(true);
                        textBox3.setClickable(true);
                        textBox4.setClickable(true);
                    }
                }, 3000);
            }
        });
        textBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler(Looper.getMainLooper());
                quit.setClickable(false);
                textBox1.setClickable(false);
                textBox2.setClickable(false);
                textBox3.setClickable(false);
                textBox4.setClickable(false);
                _text4 = text7.getText().toString().trim() + text8.getText().toString().trim();
                if(quizModelarrayList.get(currentpos).getAnswer().trim().equals(_text4)){
                    currentScore++;
                    setCartoonImage();
                    setCartoonRightAnimation();
                    textBox4.setBackground(getDrawable(R.drawable.right_answer_shape));
                }else{
                    setCartoonWrongAnimation();
                    textBox4.setBackground(getDrawable(R.drawable.wrong_answer_shape));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textBox4.setBackground(getDrawable(R.drawable.square_shape));
                        attempted_qts.setText(questionAttempted+"/"+"10");
                        questionAttempted++;
                        currentpos = random.nextInt(quizModelarrayList.size());
                        setDataView(currentpos);
                        quit.setClickable(true);
                        textBox1.setClickable(true);
                        textBox2.setClickable(true);
                        textBox3.setClickable(true);
                        textBox4.setClickable(true);
                    }
                }, 3000);
            }
        });
    }

    private void setCartoonImage() {

     if(currentScore == 1){
    counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_01_h600));
    }
     else if(currentScore == 2){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_02_h600));
         if(viewPager.getTag().equals("phone")) {
             RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lp.setMargins(80, 0, 0, 120);
             cartoon_place.setLayoutParams(lp);
         }else if(viewPager.getTag().equals("tablet")){
             RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lpp.setMargins(0, 0, 0, 0);
             cartoon_place.setLayoutParams(lpp);
         }
   }
     else if(currentScore == 3){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_03_h600));
         if(viewPager.getTag().equals("phone")) {
             RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lp.setMargins(300, 0, 0, 120);
             cartoon_place.setLayoutParams(lp);
         }else if(viewPager.getTag().equals("tablet")){
             RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lpp.setMargins(0, 0, 0, 0);
             cartoon_place.setLayoutParams(lpp);
         }
     }
     else if(currentScore == 4){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_04_h600));
         if(viewPager.getTag().equals("phone")) {
             RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lp.setMargins(230, 0, 0, 210);
             cartoon_place.setLayoutParams(lp);
         }else if(viewPager.getTag().equals("tablet")){
             RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lpp.setMargins(0, 0, 0, 0);
             cartoon_place.setLayoutParams(lpp);
         }
     }
     else if(currentScore == 5){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_05_h600));
         if(viewPager.getTag().equals("phone")) {
             RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lp.setMargins(180, 0, 0, 280);
             cartoon_place.setLayoutParams(lp);
         }else if(viewPager.getTag().equals("tablet")){
             RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lpp.setMargins(0, 0, 0, 0);
             cartoon_place.setLayoutParams(lpp);
         }
     }
     else if(currentScore == 6){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_06_h600));
         if(viewPager.getTag().equals("phone")) {
             RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lp.setMargins(380, 0, 0, 280);
             cartoon_place.setLayoutParams(lp);
         }else if(viewPager.getTag().equals("tablet")){
             RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(cartoon_place.getLayoutParams());
             lpp.setMargins(0, 0, 0, 0);
             cartoon_place.setLayoutParams(lpp);
         }
     }
     else if(currentScore == 7){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_07_h600));
     }
     else if(currentScore == 8){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_08_h600));
     }
     else if(currentScore == 9){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_09_h600));
     }
     else if(currentScore == 10){
         counting_Image.setImageDrawable(getDrawable(R.drawable.hopsc_10_h600));
     }
     else{

     }
    }

    public void reverseTimer(){
        countDownTimer = new CountDownTimer(count * 1000, 1000){
            public void onTick(long millisUntilFinished){
                counter.setText(String.valueOf(count));
                count--;
            }
            public void onFinish(){
                finishTime = finishTime - count;
                Intent intent = new Intent(MainActivity.this, page4.class);
                intent.putExtra("score", currentScore);
                intent.putExtra("countTime",finishTime);
                countDownTimer.cancel();
                startActivity(intent);
                finish();
            }
        }.start();
    }
    private void setCartoonWrongAnimation() {
        if(cart == 1){
            setRedWrongAnswer();
        }
        else if(cart == 2){
            setBlueWrongAnswer();
        }
        else if(cart == 3){
            setGreenWrongAnswer();
        }
        else if(cart == 4){
            setPinkWrongAnswer();
        }
        else{
            setBlackWrongAnswer();
        }
    }
    private void setCartoonRightAnimation() {
        if(cart == 1){
            if(currentScore < 9) {
                setRedRightAnswer();
            }else if(currentScore == 10){
                setJumpRed();
            }
        }
        else if(cart == 2){
            if(currentScore < 9) {
                setBlueRightAnswer();
            }else if(currentScore == 10){
                setJumpBlue();
            }
        }
        else if(cart == 3){
            if(currentScore < 9) {
                setGreenRightAnswer();
            }else if(currentScore == 10){
                setJumpGreen();
            }
        }
        else if(cart == 4){
            if(currentScore < 9) {
                setPinkRightAnswer();
            }else if(currentScore == 10){
                setJumpPink();
            }
        }
        else{
            if(currentScore < 9) {
                setBlackRightAnswer();
            }else if(currentScore == 10){
                setJumpBlack();
            }
        }
    }

    private void setRedRightAnswer() {
        animation = new AnimationDrawable();
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
        animation.setOneShot(false);
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setBlueRightAnswer() {
        animation = new AnimationDrawable();
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
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setGreenRightAnswer() {
        animation = new AnimationDrawable();
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
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setPinkRightAnswer() {
        animation = new AnimationDrawable();
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
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setBlackRightAnswer() {
        animation = new AnimationDrawable();
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
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }

    private void setRedWrongAnswer(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_red_00057),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setBlueWrongAnswer(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_blue_00057),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setGreenWrongAnswer(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_green_00057),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setPinkWrongAnswer(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_pink_00057),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }
    private void setBlackWrongAnswer(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.darnit_black_00057),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
    }

    private void setJumpBlack(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00017),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00018),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00019),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00020),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00021),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00022),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00023),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00024),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00025),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_black_00099),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
   }
    private void setJumpGreen(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00017),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00018),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00019),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00020),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00021),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00022),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00023),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00024),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00025),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_green_00099),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
   }
    private void setJumpRed(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00017),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00018),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00019),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00020),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00021),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00022),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00023),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00024),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00025),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_red_00099),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
   }
    private void setJumpBlue(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00017),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00018),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00019),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00020),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00021),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00022),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00023),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00024),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00025),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_blue_00099),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
   }
    private void setJumpPink(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00017),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00018),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00019),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00020),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00021),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00022),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00023),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00024),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00025),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00026),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00027),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00028),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00029),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00030),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00031),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00032),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00033),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00034),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00035),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00036),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00037),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00038),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00039),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00040),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00041),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00042),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00043),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00044),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00045),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00046),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00047),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00048),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00049),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00050),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00051),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00052),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00053),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00054),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00055),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00056),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00057),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00058),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00059),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00060),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00061),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00062),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00063),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00064),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00065),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00066),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00067),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00068),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00069),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00070),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00071),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00072),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00073),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00074),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00075),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00076),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00077),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00078),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00079),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00080),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00081),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00082),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00083),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00084),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00085),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00086),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00087),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00088),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00089),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00090),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00091),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00092),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00093),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00094),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00095),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00096),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00097),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00098),50);
        animation.addFrame(getResources().getDrawable(R.drawable.dance_pink_00099),50);
        animation.setOneShot(true);
        cartoon_place.setImageDrawable(animation);
        animation.start();
   }

    public void setOne(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("9","12","1","4","2","5","2","3","3","4","34"));
        quizModelarrayList.add(new QuizModel("3","12","1","2","1","3","1","4","1","6","14"));
        quizModelarrayList.add(new QuizModel("2","8","1","4","1","6","2","3","1","3","16"));
        quizModelarrayList.add(new QuizModel("2","4","1","2","1","8","1","4","1","3","12"));
        quizModelarrayList.add(new QuizModel("4","10","1","2","2","5","1","10","1","5","25"));
        quizModelarrayList.add(new QuizModel("2","6","1","4","2","3","1","3","1","2","13"));
        quizModelarrayList.add(new QuizModel("8","12","2","3","1","6","1","2","1","3","23"));
        quizModelarrayList.add(new QuizModel("2","12","1","4","1","3","1","2","1","6","16"));
        quizModelarrayList.add(new QuizModel("10","12","4","5","2","3","3","4","5","6","56"));
        quizModelarrayList.add(new QuizModel("4","12","1","2","1","4","1","3","1","6","13"));
        quizModelarrayList.add(new QuizModel("3","9","5","6","2","3","1","3","1","6","13"));
        quizModelarrayList.add(new QuizModel("2","10","1","3","1","5","1","2","1","4","15"));
        quizModelarrayList.add(new QuizModel("6","12","1","6","2","3","1","3","1","2","12"));
        quizModelarrayList.add(new QuizModel("6","8","3","4","1","6","2","5","2","3","34"));
        quizModelarrayList.add(new QuizModel("9","12","5","6","1","2","2","3","3","4","34"));
        quizModelarrayList.add(new QuizModel("3","6","1","3","2","3","3","4","1","2","12"));
        quizModelarrayList.add(new QuizModel("4","12","1","6","1","4","1","3","1","2","13"));
        quizModelarrayList.add(new QuizModel("8","10","4","5","1","2","3","4","2","3","45"));
        quizModelarrayList.add(new QuizModel("6","9","2","5","3","4","1","2","2","3","23"));
        quizModelarrayList.add(new QuizModel("6","10","3","5","2","5","4","5","1","2","35"));
        quizModelarrayList.add(new QuizModel("4","8","1","3","1","2","3","4","1","4","12"));
        quizModelarrayList.add(new QuizModel("4","6","1","3","2","3","3","4","1","2","23"));
        quizModelarrayList.add(new QuizModel("5","10","1","2","3","5","2","5","1","4","12"));
        quizModelarrayList.add(new QuizModel("8","12","1","2","4","5","2","3","3","4","23"));
        quizModelarrayList.add(new QuizModel("10","12","5","6","1","2","3","4","2","3","56"));
    }
    public void setTwo(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("2","14","1","3","1","2","1","7","2","7","17"));
        quizModelarrayList.add(new QuizModel("3","15","1","5","1","3","1","2","2","3","15"));
        quizModelarrayList.add(new QuizModel("10","15","4","5","3","5","2","5","2","3","23"));
        quizModelarrayList.add(new QuizModel("8","16","1","4","3","4","1","2","2","3","12"));
        quizModelarrayList.add(new QuizModel("12","15","1","2","4","5","3","5","2","5","45"));
        quizModelarrayList.add(new QuizModel("8","14","4","7","2","7","1","2","3","7","47"));
        quizModelarrayList.add(new QuizModel("9","15","2","5","3","8","3","4","3","5","35"));
        quizModelarrayList.add(new QuizModel("6","16","1","3","1","4","1","8","3","8","38"));
        quizModelarrayList.add(new QuizModel("10","14","2","3","3","4","5","7","1","2","57"));
        quizModelarrayList.add(new QuizModel("3","15","1","6","1","5","1","4","1","3","15"));
        quizModelarrayList.add(new QuizModel("10","15","2","3","3","5","3","4","1","2","23"));
        quizModelarrayList.add(new QuizModel("4","14","1","7","3","7","4","7","2","7","27"));
        quizModelarrayList.add(new QuizModel("12","16","3","4","1","3","3","8","2","3","34"));
        quizModelarrayList.add(new QuizModel("5","15","1","3","1","2","2","5","1","5","13"));
        quizModelarrayList.add(new QuizModel("10","16","2","3","5","7","5","8","1","2","58"));
        quizModelarrayList.add(new QuizModel("6","15","1","3","2","5","1","2","3","5","25"));
        quizModelarrayList.add(new QuizModel("4","16","2","5","1","4","1","2","1","3","14"));
        quizModelarrayList.add(new QuizModel("5","15","2","3","2","5","1","5","1","3","13"));
        quizModelarrayList.add(new QuizModel("14","16","2","3","3","4","7","8","6","7","78"));
        quizModelarrayList.add(new QuizModel("6","14","3","7","2","5","1","3","2","7","37"));
        quizModelarrayList.add(new QuizModel("12","15","5","8","3","4","2","3","4","5","45"));
        quizModelarrayList.add(new QuizModel("2","16","1","5","1","8","1","3","1","4","18"));
        quizModelarrayList.add(new QuizModel("6","15","2","5","1","4","1","2","1","3","25"));
        quizModelarrayList.add(new QuizModel("12","14","2","3","3","4","5","6","6","7","67"));
        quizModelarrayList.add(new QuizModel("9","15","2","3","3","4","3","5","1","2","35"));
    }
    public void setThree(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("8","10","1","2","3","4","2","3","4","5","45"));
        quizModelarrayList.add(new QuizModel("4","20","1","4","1","3","1","5","1","2","15"));
        quizModelarrayList.add(new QuizModel("2","4","1","2","1","4","1","5","1","3","12"));
        quizModelarrayList.add(new QuizModel("2","12","1","4","1","6","1","3","1","2","16"));
        quizModelarrayList.add(new QuizModel("10","15","2","3","3","4","1","2","5","6","23"));
        quizModelarrayList.add(new QuizModel("4","8","1","2","1","4","2","3","1","3","12"));
        quizModelarrayList.add(new QuizModel("6","10","1","3","3","5","2","3","2","5","35"));
        quizModelarrayList.add(new QuizModel("9","12","2","3","3","4","1","3","4","5","34"));
        quizModelarrayList.add(new QuizModel("3","6","1","2","5","6","1","4","1","3","12"));
        quizModelarrayList.add(new QuizModel("5","15","1","2","1","6","1","4","1","3","13"));
        quizModelarrayList.add(new QuizModel("6","8","2","3","5","6","1","2","3","4","34"));
        quizModelarrayList.add(new QuizModel("10","12","5","6","3","4","2","3","1","2","23"));
        quizModelarrayList.add(new QuizModel("4","10","4","5","1","2","3","5","2","5","25"));
        quizModelarrayList.add(new QuizModel("4","20","1","3","1","4","1","2","1","5","13"));
        quizModelarrayList.add(new QuizModel("3","12","1","5","1","2","1","4","1","3","13"));
        quizModelarrayList.add(new QuizModel("2","6","1","6","1","3","1","4","1","2","13"));
        quizModelarrayList.add(new QuizModel("6","12","2","5","1","4","1","2","1","3","12"));
        quizModelarrayList.add(new QuizModel("2","20","2","5","1","6","1","10","1","5","110"));
        quizModelarrayList.add(new QuizModel("2","8","1","4","1","5","1","2","1","3","14"));
        quizModelarrayList.add(new QuizModel("6","20","1","3","2","5","3","10","3","5","310"));
        quizModelarrayList.add(new QuizModel("4","12","1","2","1","4","2","5","1","3","13"));
        quizModelarrayList.add(new QuizModel("5","20","1","3","1","2","2","5","1","4","14"));
        quizModelarrayList.add(new QuizModel("4","6","1","2","4","5","2","3","3","4","23"));
        quizModelarrayList.add(new QuizModel("8","12","3","4","2","3","1","2","5","6","23"));
        quizModelarrayList.add(new QuizModel("2","10","1","2","1","5","2","5","1","3","15"));
    }
    public void setFour(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("2","12","1","3","1","4","1","8","1","6","16"));
        quizModelarrayList.add(new QuizModel("3","12","1","6","1","3","1","4","1","2","14"));
        quizModelarrayList.add(new QuizModel("4","12","1","3","1","6","1","4","1","2","13"));
        quizModelarrayList.add(new QuizModel("6","12","1","2","3","4","2","4","1","3","12"));
        quizModelarrayList.add(new QuizModel("8","12","1","2","2","3","3","4","4","5","23"));
        quizModelarrayList.add(new QuizModel("10","12","1","2","1","3","5","6","2","3","56"));
        quizModelarrayList.add(new QuizModel("2","20","1","3","1","5","1","10","1","2","110"));
        quizModelarrayList.add(new QuizModel("4","20","2","5","1","3","1","4","1","5","15"));
        quizModelarrayList.add(new QuizModel("5","20","2","5","1","10","1","4","1","5","14"));
        quizModelarrayList.add(new QuizModel("6","20","3","10","3","5","3","20","1","2","310"));
        quizModelarrayList.add(new QuizModel("8","20","4","5","2","5","1","2","1","3","25"));
        quizModelarrayList.add(new QuizModel("12","20","1","2","3","5","4","5","2","5","35"));
        quizModelarrayList.add(new QuizModel("14","20","7","10","3","4","2","3","3","5","710"));
        quizModelarrayList.add(new QuizModel("15","20","3","5","1","2","2","3","3","4","34"));
        quizModelarrayList.add(new QuizModel("16","20","1","2","2","3","4","5","3","4","45"));
        quizModelarrayList.add(new QuizModel("18","20","9","10","2","3","3","5","4","5","910"));
        quizModelarrayList.add(new QuizModel("3","24","1","3","1","4","1","6","1","8","18"));
        quizModelarrayList.add(new QuizModel("4","24","1","4","1","3","3","8","1","6","16"));
        quizModelarrayList.add(new QuizModel("6","24","1","6","1","3","1","4","1","2","14"));
        quizModelarrayList.add(new QuizModel("8","24","2","5","1","3","2","3","1","2","13"));
        quizModelarrayList.add(new QuizModel("10","24","5","12","1","6","1","2","1","3","512"));
        quizModelarrayList.add(new QuizModel("12","24","1","2","2","3","1","4","1","3","12"));
        quizModelarrayList.add(new QuizModel("15","24","1","3","5","8","1","2","2","5","58"));
        quizModelarrayList.add(new QuizModel("16","24","3","8","3","4","2","3","1","2","23"));
        quizModelarrayList.add(new QuizModel("18","24","1","2","2","3","1","3","3","4","34"));
    }
    public void setFive(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("2","18","1","4","1","3","1","9","1","2","19"));
        quizModelarrayList.add(new QuizModel("3","18","1","6","1","4","1","2","1","3","16"));
        quizModelarrayList.add(new QuizModel("4","18","1","3","2","3","1","9","2","9","29"));
        quizModelarrayList.add(new QuizModel("6","18","1","2","3","4","2","3","1","3","13"));
        quizModelarrayList.add(new QuizModel("8","18","1","3","2","5","4","9","3","4","49"));
        quizModelarrayList.add(new QuizModel("9","18","2","3","1","2","1","4","1","3","12"));
        quizModelarrayList.add(new QuizModel("10","18","5","9","1","2","3","4","2","3","59"));
        quizModelarrayList.add(new QuizModel("12","18","3","5","2","3","1","2","3","4","23"));
        quizModelarrayList.add(new QuizModel("14","18","3","4","7","9","1","2","2","3","79"));
        quizModelarrayList.add(new QuizModel("15","18","5","6","3","4","1","2","3","5","56"));
        quizModelarrayList.add(new QuizModel("16","18","8","9","1","2","3","4","2","3","89"));
        quizModelarrayList.add(new QuizModel("2","20","1","2","1","4","1","10","1","5","110"));
        quizModelarrayList.add(new QuizModel("4","20","1","4","1","3","1","2","1","5","15"));
        quizModelarrayList.add(new QuizModel("5","20","2","5","3","10","1","4","1","3","14"));
        quizModelarrayList.add(new QuizModel("6","20","1","2","3","5","3","10","1","3","310"));
        quizModelarrayList.add(new QuizModel("8","20","2","5","1","2","1","3","1","4","25"));
        quizModelarrayList.add(new QuizModel("10","20","2","5","1","4","1","3","1","2","12"));
        quizModelarrayList.add(new QuizModel("12","20","2","5","1","2","3","5","3","4","35"));
        quizModelarrayList.add(new QuizModel("14","20","3","5","7","10","2","3","3","4","710"));
        quizModelarrayList.add(new QuizModel("15","20","3","4","3","4","1","2","7","10","34"));
        quizModelarrayList.add(new QuizModel("16","20","3","4","1","3","4","5","7","10","45"));
        quizModelarrayList.add(new QuizModel("18","20","3","4","9","10","2","3","4","5","b"));
        quizModelarrayList.add(new QuizModel("16","24","3","8","3","4","5","6","2","3","23"));
        quizModelarrayList.add(new QuizModel("20","24","5","6","1","2","2","3","3","4","56"));
        quizModelarrayList.add(new QuizModel("22","24","3","4","3","10","7","10","11","12","1112"));
    }
    public void setSix(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("8","20","1","3","1","4","1","2","2","5","25"));
        quizModelarrayList.add(new QuizModel("10","20","1","2","1","4","2","5","3","5","12"));
        quizModelarrayList.add(new QuizModel("12","20","2","5","3","5","1","3","2","3","35"));
        quizModelarrayList.add(new QuizModel("14","20","7","10","3","5","1","2","2","5","710"));
        quizModelarrayList.add(new QuizModel("15","20","5","6","1","2","3","4","2","3","34"));
        quizModelarrayList.add(new QuizModel("16","20","3","8","5","8","4","5","3","4","45"));
        quizModelarrayList.add(new QuizModel("18","20","2","3","4","5","3","4","9","10","910"));
        quizModelarrayList.add(new QuizModel("4","24","1","4","1","6","1","2","1","8","16"));
        quizModelarrayList.add(new QuizModel("6","24","1","3","1","4","1","6","1","2","14"));
        quizModelarrayList.add(new QuizModel("8","24","1","3","1","2","3","8","1","4","13"));
        quizModelarrayList.add(new QuizModel("12","24","1","3","1","2","3","8","1","4","31"));
        quizModelarrayList.add(new QuizModel("16","24","1","2","3","4","3","8","2","3","23"));
        quizModelarrayList.add(new QuizModel("20","24","2","3","3","4","4","5","5","6","56"));
        quizModelarrayList.add(new QuizModel("5","30","1","2","1","4","1","6","1","3","16"));
        quizModelarrayList.add(new QuizModel("9","30","3","10","1","6","3","5","2","5","310"));
        quizModelarrayList.add(new QuizModel("10","30","1","6","1","3","1","4","1","2","13"));
        quizModelarrayList.add(new QuizModel("15","30","1","3","1","2","2","5","5","6","12"));
        quizModelarrayList.add(new QuizModel("18","30","3","5","1","2","2","5","2","5","35"));
        quizModelarrayList.add(new QuizModel("20","30","3","4","5","6","2","3","1","2","23"));
        quizModelarrayList.add(new QuizModel("27","30","3","4","2","5","4","5","9","10","910"));
        quizModelarrayList.add(new QuizModel("5","40","1","3","1","2","1","8","1","6","18"));
        quizModelarrayList.add(new QuizModel("10","40","1","3","3","8","1","4","1","2","14"));
        quizModelarrayList.add(new QuizModel("15","40","1","3","3","8","1","4","1","2","38"));
        quizModelarrayList.add(new QuizModel("16","40","3","8","3","4","1","2","2","5","25"));
        quizModelarrayList.add(new QuizModel("18","40","9","20","2","3","5","6","3","4","920"));
    }
    public void setSeven(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("3","36","1","4","1","3","1","2","1","12","112"));
        quizModelarrayList.add(new QuizModel("4","36","1","9","1","6","1","2","1","3","19"));
        quizModelarrayList.add(new QuizModel("6","36","1","8","1","6","1","3","1","4","16"));
        quizModelarrayList.add(new QuizModel("8","36","1","3","1","2","2","3","2","9","29"));
        quizModelarrayList.add(new QuizModel("9","36","2","9","1","2","1","4","1","3","14"));
        quizModelarrayList.add(new QuizModel("12","36","2","9","3","8","1","3","1","4","13"));
        quizModelarrayList.add(new QuizModel("15","36","5","12","2","5","3","5","3","8","512"));
        quizModelarrayList.add(new QuizModel("18","36","5","6","1","6","1","4","1","2","12"));
        quizModelarrayList.add(new QuizModel("20","36","1","2","5","6","2","3","5","9","59"));
        quizModelarrayList.add(new QuizModel("24","36","5","8","3","8","2","3","3","4","23"));
        quizModelarrayList.add(new QuizModel("30","36","1","2","5","6","5","9","4","9","56"));
        quizModelarrayList.add(new QuizModel("32","36","7","8","3","4","8","9","2","3","89"));
        quizModelarrayList.add(new QuizModel("4","48","1","6","1","2","1","12","1","4","112"));
        quizModelarrayList.add(new QuizModel("6","48","1","8","1","6","1","4","1","2","18"));
        quizModelarrayList.add(new QuizModel("8","48","1","4","1","8","1","6","1","3","16"));
        quizModelarrayList.add(new QuizModel("12","48","1","4","1","8","1","2","1","6","14"));
        quizModelarrayList.add(new QuizModel("16","48","1","6","1","3","1","4","1","2","13"));
        quizModelarrayList.add(new QuizModel("20","48","3","8","5","12","1","2","2","3","512"));
        quizModelarrayList.add(new QuizModel("24","48","1","2","1","4","3","8","1","3","12"));
        quizModelarrayList.add(new QuizModel("28","48","1","2","2","3","7","12","5","6","712"));
        quizModelarrayList.add(new QuizModel("30","48","4","5","3","4","2","3","5","8","58"));
        quizModelarrayList.add(new QuizModel("32","48","3","4","1","2","2","3","5","6","25"));
        quizModelarrayList.add(new QuizModel("36","48","1","2","3","4","5","6","2","3","34"));
        quizModelarrayList.add(new QuizModel("40","48","3","8","5","6","5","8","1","2","56"));
        quizModelarrayList.add(new QuizModel("44","48","11","12","5","6","22","24","3","4","1112"));
    }
    public void setEight(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("10","40","1","4","1","3","3","8","1","2","14"));
        quizModelarrayList.add(new QuizModel("12","40","1","4","2","3","2","5","3","10","310"));
        quizModelarrayList.add(new QuizModel("20","40","1","2","1","3","5","6","3","4","12"));
        quizModelarrayList.add(new QuizModel("28","40","5","8","1","2","7","10","2","3","710"));
        quizModelarrayList.add(new QuizModel("15","45","5","8","1","2","1","3","5","6","13"));
        quizModelarrayList.add(new QuizModel("20","45","3","4","4","9","1","2","2","3","49"));
        quizModelarrayList.add(new QuizModel("20","50","3","5","1","3","1","4","2","5","25"));
        quizModelarrayList.add(new QuizModel("25","50","1","4","1","3","2","3","1","2","12"));
        quizModelarrayList.add(new QuizModel("35","50","7","10","5","6","3","4","2","3","710"));
        quizModelarrayList.add(new QuizModel("40","60","5","8","2","3","5","6","3","4","23"));
        quizModelarrayList.add(new QuizModel("45","60","5","6","1","2","3","4","2","3","34"));
        quizModelarrayList.add(new QuizModel("55","60","5","6","11","12","3","4","5","8","1112"));
        quizModelarrayList.add(new QuizModel("15","75","1","3","1","5","1","2","1","4","15"));
        quizModelarrayList.add(new QuizModel("20","75","1","3","3","5","4","15","2","5","415"));
        quizModelarrayList.add(new QuizModel("40","75","8","15","4","5","2","3","1","2","815"));
        quizModelarrayList.add(new QuizModel("50","75","5","6","3","4","1","2","2","3","34"));
        quizModelarrayList.add(new QuizModel("60","75","5","8","4","5","7","10","2","3","710"));
        quizModelarrayList.add(new QuizModel("10","80","1","6","1","3","1","8","1","4","13"));
        quizModelarrayList.add(new QuizModel("12","80","1","4","3","20","2","5","1","10","320"));
        quizModelarrayList.add(new QuizModel("24","80","3","10","1","3","2","5","1","4","310"));
        quizModelarrayList.add(new QuizModel("25","80","3","8","5","16","1","6","1","3","516"));
        quizModelarrayList.add(new QuizModel("40","80","2","3","1","2","1","3","5","8","12"));
        quizModelarrayList.add(new QuizModel("50","80","5","8","1","3","5","6","2","3","58"));
        quizModelarrayList.add(new QuizModel("55","80","2","3","3","4","11","16","5","8","1116"));
        quizModelarrayList.add(new QuizModel("60","80","3","8","5","8","3","4","2","3","34"));
    }
    public void setNine(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("25","200","1","6","1","5","1","8","1","4","18"));
        quizModelarrayList.add(new QuizModel("50","200","1","8","1","4","1","6","1","3","14"));
        quizModelarrayList.add(new QuizModel("75","200","1","6","1","3","3","8","1","4","38"));
        quizModelarrayList.add(new QuizModel("80","200","3","10","1","3","1","2","2","5","25"));
        quizModelarrayList.add(new QuizModel("100","200","1","2","1","3","1","4","4","5","12"));
        quizModelarrayList.add(new QuizModel("110","200","4","5","7","8","11","20","9","10","1120"));
        quizModelarrayList.add(new QuizModel("120","200","2","3","3","4","3","5","4","5","35"));
        quizModelarrayList.add(new QuizModel("125","200","5","8","2","3","3","5","3","4","58"));
        quizModelarrayList.add(new QuizModel("140","200","4","5","7","10","5","6","3","4","710"));
        quizModelarrayList.add(new QuizModel("150","200","3","8","3","4","1","2","2","3","34"));
        quizModelarrayList.add(new QuizModel("160","200","4","5","3","4","2","3","3","5","45"));
        quizModelarrayList.add(new QuizModel("175","200","3","4","2","3","9","10","7","8","78"));
        quizModelarrayList.add(new QuizModel("180","200","3","4","2","3","9","10","7","8","78"));
        quizModelarrayList.add(new QuizModel("50","300","1","6","1","4","1","10","1","3","14"));
        quizModelarrayList.add(new QuizModel("60","300","1","5","1","6","1","3","1","4","13"));
        quizModelarrayList.add(new QuizModel("75","300","2","5","1","2","1","4","1","3","14"));
        quizModelarrayList.add(new QuizModel("90","300","3","5","1","4","2","5","3","10","310"));
        quizModelarrayList.add(new QuizModel("100","300","1","5","1","4","1","2","1","3","13"));
        quizModelarrayList.add(new QuizModel("120","300","1","6","2","5","1","4","1","3","25"));
        quizModelarrayList.add(new QuizModel("150","300","1","3","1","4","1","2","1","5","12"));
        quizModelarrayList.add(new QuizModel("180","300","1","3","3","5","1","2","2","5","35"));
        quizModelarrayList.add(new QuizModel("200","300","3","5","2","3","2","5","1","3","23"));
        quizModelarrayList.add(new QuizModel("225","300","3","4","5","8","2","3","1","2","34"));
        quizModelarrayList.add(new QuizModel("250","300","4","5","2","3","3","4","5","6","56"));
        quizModelarrayList.add(new QuizModel("270","300","9","10","2","3","1","2","4","5","910"));
    }
    public void setTen(ArrayList<QuizModel> quizModelarrayList){
        quizModelarrayList.add(new QuizModel("75","100","5","6","3","4","1","2","2","3","34"));
        quizModelarrayList.add(new QuizModel("80","100","3","5","1","2","3","4","4","5","45"));
        quizModelarrayList.add(new QuizModel("20","150","2","15","1","2","1","5","1","3","215"));
        quizModelarrayList.add(new QuizModel("30","150","1","5","1","3","1","4","1","6","15"));
        quizModelarrayList.add(new QuizModel("50","150","1","5","1","4","1","3","1","2","13"));
        quizModelarrayList.add(new QuizModel("75","150","1","6","1","2","1","3","1","5","12"));
        quizModelarrayList.add(new QuizModel("100","150","5","6","2","3","3","5","2","5","23"));
        quizModelarrayList.add(new QuizModel("125","150","3","4","2","3","4","5","5","6","56"));
        quizModelarrayList.add(new QuizModel("50","250","1","3","1","4","1","5","1","6","15"));
        quizModelarrayList.add(new QuizModel("100","250","1","3","1","2","2","5","3","5","25"));
        quizModelarrayList.add(new QuizModel("125","250","1","2","3","8","1","4","1","3","12"));
        quizModelarrayList.add(new QuizModel("150","250","1","2","3","5","1","3","3","8","35"));
        quizModelarrayList.add(new QuizModel("175","150","4","5","7","10","2","3","3","4","710"));
        quizModelarrayList.add(new QuizModel("200","250","4","5","5","6","3","4","2","3","45"));
        quizModelarrayList.add(new QuizModel("225","250","4","5","5","8","2","3","9","10","910"));
        quizModelarrayList.add(new QuizModel("100","500","2","5","1","3","1","5","1","4","15"));
        quizModelarrayList.add(new QuizModel("150","500","3","10","2","5","1","2","1","5","310"));
        quizModelarrayList.add(new QuizModel("200","500","3","5","1","3","1","2","2","5","25"));
        quizModelarrayList.add(new QuizModel("250","500","3","5","2","5","2","3","1","2","12"));
        quizModelarrayList.add(new QuizModel("275","500","7","10","3","4","11","20","9","10","1120"));
        quizModelarrayList.add(new QuizModel("300","500","3","5","2","3","1","2","4","5","35"));
        quizModelarrayList.add(new QuizModel("325","500","7","10","4","5","13","20","3","5","1320"));
        quizModelarrayList.add(new QuizModel("350","500","2","3","1","2","7","10","3","4","710"));
        quizModelarrayList.add(new QuizModel("400","500","1","2","7","8","3","4","4","5","45"));
        quizModelarrayList.add(new QuizModel("450","500","2","5","9","10","3","4","4","5","910"));
    }
}