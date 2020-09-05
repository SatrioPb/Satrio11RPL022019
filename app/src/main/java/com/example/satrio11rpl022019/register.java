package com.example.satrio11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText mama;
    EditText emil;
    EditText pasw;
    EditText hp;
    Button dft;
    Button bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mama = findViewById(R.id.mama);
        emil = findViewById(R.id.emil);
        pasw = findViewById(R.id.pasw);
        hp = findViewById(R.id.hp);
        dft = findViewById(R.id.dft);
        bck = findViewById(R.id.bck);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(register.this,"Anda berhasil kembali ", Toast.LENGTH_SHORT).show();
                Intent dtr = new Intent(register.this, MainActivity.class);
                startActivity(dtr);
                finish();
            }
        });


    }
}