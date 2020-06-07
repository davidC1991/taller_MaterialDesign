package com.example.material_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AgregarPersona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
    }


    public void onBackPressed(){
        //finish();
        Intent i= new Intent(AgregarPersona.this,MainActivity.class);
        startActivity(i);
    }
}
