package com.example.material_design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab;
        RecyclerView recyclerViewAdaptador;
        final RecylcerViewAdaptador adaptadorCarro;
        DatabaseReference databaseReference;
        String db= "Personas";
        final ArrayList<Carro> carros;

        recyclerViewAdaptador= findViewById(R.id.recyclerCarros);
        recyclerViewAdaptador.setLayoutManager(new LinearLayoutManager(this));
        carros= new ArrayList<>();
        adaptadorCarro=new RecylcerViewAdaptador(obtenerCarros());
        recyclerViewAdaptador.setAdapter(adaptadorCarro);

        fab = findViewById(R.id.fab_agregar);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carros.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Carro p= snapshot.getValue(Carro.class);
                        carros.add(p);
                    }
                }
                adaptadorCarro.notifyDataSetChanged();
                Datos.setPersonas(carros);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void agregar(View v){
        Intent intent;
        intent= new Intent(MainActivity.this, AgregarPersona.class);
        startActivity(intent);
        finish();
    }

    public List<Carro> obtenerCarros(){
        List<Carro> carro= new ArrayList<>();
        carro.add(new Carro("Ferrari","Rojo","4fr456","432.000 dolares","250 km/h",R.drawable.ferrari,"FDFD"));
        carro.add(new Carro("Bugatti","Negro","dfg456","546.000 dolares","270 km/h",R.drawable.bugatti,"fs"));
        carro.add(new Carro("Porche","azul","4se456","300.000 dolares","220 km/h",R.drawable.porche,"ds"));
        carro.add(new Carro("Lanbroghini","Rojo","4dr566","340.000 dolares","370 km/h",R.drawable.lanborghini,"ds"));

        return carro;
    }
}
