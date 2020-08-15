package com.example.satrio11rpl022019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class control extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
    if (sharedPreferences.getString("userid","").isEmpty()){
        Intent in = new  Intent(getApplicationContext(),MainActivity.class);
        startActivity(in);
    }else {
Intent in = new Intent(getApplicationContext(),menu.class);
startActivity(in);
finish();
    }
    }
}
