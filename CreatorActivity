package com.example.ktbilgiyarisi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreatorActivity extends AppCompatActivity {
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);
        dataHelper = new DataHelper(this);
        Button anasay;
        anasay = (Button) findViewById(R.id.home);

        anasay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreatorActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
