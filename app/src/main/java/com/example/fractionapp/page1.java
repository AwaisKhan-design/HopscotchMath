package com.example.fractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class page1 extends AppCompatActivity {
    previousScore previousScore;
    Button next_btn, direction_btn;
    TextView addbtn1, increment_level, decrement_level,number, decrement_btn, second, note1, note2, webLink;
    int _number;
    static String level = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        previousScore = new previousScore(getApplicationContext());
        findViews();
        if(previousScore.getScore().equals("")){
            note2.setText("High Score 0/10 ( ");
        }else{
            note2.setText("High Scorce "+previousScore.getScore()+"/"+"10"+" ( ");
        }
        _number = Integer.parseInt(number.getText().toString());
        level = note1.getText().toString();
        actionListners();
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
    }
    private void actionListners() {
        webLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLink.setMovementMethod(LinkMovementMethod.getInstance());
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
                    if(_number < 12) {
                        String num = String.valueOf(++_number);
                        number.setText(num);
                        decrement_btn.setVisibility(View.VISIBLE);
                        if(_number == 12){
                            addbtn1.setVisibility(View.INVISIBLE);
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
                    if (_number <= 12 || _number > 0) {
                        String num = String.valueOf(--_number);
                        number.setText(num);
                        if(_number == 1){
                            decrement_btn.setVisibility(View.INVISIBLE);
                        }
                        else if(_number < 12){
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