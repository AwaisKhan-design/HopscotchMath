package com.example.fractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class page2 extends AppCompatActivity {
Button backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        backbtn=findViewById(R.id.back_btn);
        actionListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(page2.this, page1.class);
        startActivity(i);
        finish();
    }

    private void actionListener() {
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(page2.this, page1.class);
                startActivity(i);
                finish();
            }
        });
    }
}