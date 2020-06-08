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

public class MainActivity extends AppCompatActivity implements RecylcerViewAdaptador.OnCarroClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab;
        RecyclerView recyclerViewAdaptador;
        final RecylcerViewAdaptador adaptadorCarro;
        DatabaseReference databaseReference;
        String db= "Carros";
        final ArrayList<Carro> carros;

        recyclerViewAdaptador= findViewById(R.id.recyclerCarros);
        recyclerViewAdaptador.setLayoutManager(new LinearLayoutManager(this));
        carros= new ArrayList<>();
        adaptadorCarro=new RecylcerViewAdaptador(carros, (RecylcerViewAdaptador.OnCarroClickListener) this);
        recyclerViewAdaptador.setAdapter(adaptadorCarro);

        fab = findViewById(R.id.fab_agregar);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carros.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Carro c= snapshot.getValue(Carro.class);
                        carros.add(c);
                    }
                }
                adaptadorCarro.notifyDataSetChanged();
                Datos.setCarros(carros);
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




    public void onCarroClick(Carro p){
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("marca",p.getMarca());
        bundle.putString("color",p.getColor());
        bundle.putString("placa",p.getPlaca());
        bundle.putString("velocidad",p.getVelocidad());
        bundle.putString("precio",p.getPrecio());
        bundle.putString("id",p.getId());

        intent = new Intent(MainActivity.this, DetalleCarro.class);
        intent.putExtra("datos",bundle);
        startActivity(intent);
        //finish();
    }

}
