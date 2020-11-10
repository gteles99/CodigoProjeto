package com.example.appteste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn,btn1,btn2,btns;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chamatelas();
    }
    private void chamatelas(){
        Button btn = (Button)findViewById(R.id.btn);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btns =(Button)findViewById(R.id.btns);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(MainActivity.this,Diabeticosehipertensos.class);
                startActivity(tela);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(MainActivity.this,Crianca.class);
                startActivity(tela);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(MainActivity.this,Prontuarios_em_geral.class);
                startActivity(tela);
            }
        });
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(MainActivity.this,Sobre.class);
                startActivity(tela);
            }
        });
    }
}