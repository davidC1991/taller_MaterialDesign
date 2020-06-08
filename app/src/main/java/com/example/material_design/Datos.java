package com.example.material_design;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {

    private static String db = "Carros";
    private static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public static  String getId(){
        return databaseReference.push().getKey();
    }

    public static void setPersonas(ArrayList<Carro> carros){
        carros = carros;
    }

    public static void guardar(Carro c){
        databaseReference.child(db).child(c.getId()).setValue(c);
    }


}
