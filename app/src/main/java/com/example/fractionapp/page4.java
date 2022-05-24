package com.example.fractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class page4 extends AppCompatActivity {
    previousScore previousScore;
    Button replay_btn, done_btn;
    TextView correct_ans, best_score_textView, your_score_seconds, your_best_seconds, score_remark,next_problem_set;
    int score, finishTime;
    int bestScore = 0, best_Time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        try {
            previousScore = new previousScore(getApplicationContext());
            findViews();
            Intent intent = getIntent();
            score = intent.getIntExtra("score", 0);
            finishTime = intent.getIntExtra("countTime", 0);
            setRemark();
            correct_ans.setText(score + "/" + "10");
            your_score_seconds.setText("(" + finishTime + ")");
            if (previousScore.getScore().equals("") && previousScore.getsavetime().equals("")) {
                best_score_textView.setText(score + "/" + "10");
                previousScore.savesession(String.valueOf(score));
                your_best_seconds.setText("(" + finishTime + ")");
                previousScore.savetime(String.valueOf(finishTime));
            } else {
                bestScore = Integer.parseInt(previousScore.getScore());
                best_Time = Integer.parseInt(previousScore.getsavetime());
                checkBestScore(score,best_Time);
            }
            actionListeners();
        } catch (Exception e) {
        }
    }
    private void setRemark() {
        if(score < 5){
            score_remark.setText("Need Hard Work !");
        }else if(score >= 4 && score <= 8){
            score_remark.setText("Good Job !");
        }else if(score >= 9){
            score_remark.setText("Excellent Job !");
        }
    }
    private void checkBestScore(int score, int bestTime) {
        if (score >= bestScore || finishTime <= bestTime) {
            best_score_textView.setText(score + "/" + "10");
            previousScore.savesession(String.valueOf(score));
            your_best_seconds.setText(" ("+ finishTime + ")");
            previousScore.savetime(String.valueOf(finishTime));
        } else {
            best_score_textView.setText(bestScore + "/" + "10");
            your_best_seconds.setText(" ("+ best_Time + ")");
        }
    }
    private void findViews() {
        replay_btn = findViewById(R.id.replay_btn);
        done_btn = findViewById(R.id.done_btn);
        correct_ans = findViewById(R.id.correct_ans);
        best_score_textView = findViewById(R.id.best_score_textView);
        your_score_seconds = findViewById(R.id.your_score_seconds);
        your_best_seconds = findViewById(R.id.your_best_seconds);
        score_remark = findViewById(R.id.score_remark);
        next_problem_set = findViewById(R.id.next_problem_set);
    }
    private void actionListeners() {
        replay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(page4.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){
                    Toast.makeText(page4.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next_problem_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(page4.this, page1.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){
                    Toast.makeText(page4.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}