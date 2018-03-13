package com.ozgurtas.newsapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ozgurtas.newsapplication.R;
import com.ozgurtas.newsapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Android Data Binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        intent = new Intent(MainActivity.this, StreamActivity.class);

        binding.world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", "world");
                startActivity(intent);
            }
        });

        binding.technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", "technology");
                startActivity(intent);
            }
        });

        binding.sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", "sports");
                startActivity(intent);
            }
        });
    }
}
