package com.example.material_design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAdaptador;
    private RecylcerViewAdaptador adaptadorCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab;

        recyclerViewAdaptador= findViewById(R.id.recyclerCarros);
        recyclerViewAdaptador.setLayoutManager(new LinearLayoutManager(this));

        adaptadorCarro=new RecylcerViewAdaptador(obtenerCarros());
        recyclerViewAdaptador.setAdapter(adaptadorCarro);

        fab = findViewById(R.id.fab_agregar);
    }

    public void agregar(View v){
        Intent intent;
        intent= new Intent(MainActivity.this, AgregarPersona.class);
        startActivity(intent);
        finish();
    }

    public List<carro> obtenerCarros(){
        List<carro> carro= new ArrayList<>();
        carro.add(new carro("Ferrari","Rojo","4fr456",432,250,R.drawable.ferrari));
        carro.add(new carro("Bugatti","Negro","dfg456",546,270,R.drawable.bugatti));
        carro.add(new carro("Porche","azul","4se456",332,220,R.drawable.porche));
        carro.add(new carro("Lanbroghini","Rojo","4dr566",532,300,R.drawable.lanborghini));

        return carro;
    }
}
