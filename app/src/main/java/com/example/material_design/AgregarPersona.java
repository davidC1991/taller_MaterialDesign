package com.example.material_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarPersona extends AppCompatActivity {

    private ArrayList<Integer> fotos;
    private EditText marca, color, placa,velocidad,precio;
    private StorageReference storageReference;
    private Uri uri;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        marca=findViewById(R.id.txtMarca);
        color=findViewById(R.id.txtColor);
        placa=findViewById(R.id.txtPlaca);
        velocidad=findViewById(R.id.txtVelocidad);
        precio=findViewById(R.id.txtPrecio);

        foto= findViewById(R.id.imgSeleccionada);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.bugatti);
        fotos.add(R.drawable.ferrari);
        fotos.add(R.drawable.porche);
        fotos.add(R.drawable.lanborghini);

        storageReference= FirebaseStorage.getInstance().getReference();

    }

    public void guardar(View v){
      String marca_1,placa_1,color_1,precio_1,velocidad_1,id;
      int foto;


          InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
          marca_1 = marca.getText().toString();
          placa_1 = placa.getText().toString();
          color_1 = color.getText().toString();
          precio_1 = precio.getText().toString();
          velocidad_1 = velocidad.getText().toString();
        if(validar()) {
          foto = foto_aleatoria();
          id = Datos.getId();
          Carro carro = new Carro(marca_1, color_1, placa_1, precio_1, velocidad_1, foto, id);
          carro.guardar();
          subir_Foto(id);
          limpiar();
          imp.hideSoftInputFromWindow(marca.getWindowToken(), 0);
          Snackbar.make(v, getString(R.string.carro_guardado_exitosamente), Snackbar.LENGTH_LONG).show();
      }
    }


    public void subir_Foto(String id){
        StorageReference child= storageReference.child(id);
        //Uri uri= Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        marca.setText("");
        color.setText("");
        placa.setText("");
        precio.setText("");
        velocidad.setText("");
        marca.requestFocus();
        foto.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void onBackPressed(){
        //  finish();
        Intent i= new Intent(AgregarPersona.this,MainActivity.class);
        startActivity(i);
    }


    public void seleccionar_foto(View v){
        Intent i= new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,getString(R.string.seleccionarFoto)),1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1){
            uri=data.getData();
            if(uri!=null){
                foto.setImageURI(uri);
            }
        }
    }

    public boolean validar(){
        boolean vacio=true;

        String marc= marca.getText().toString();
        String col= color.getText().toString();
        String velo= velocidad.getText().toString();
        String plac= placa.getText().toString();
        String pre= precio.getText().toString();

        if(marc.isEmpty()){
            marca.setError(getString(R.string.errorMarcaCarro));
            vacio=false;
        }
        if(col.isEmpty()){
            vacio=false;
            color.setError(getString(R.string.ErrorColorCarro));
        }
        if(velo.isEmpty()){
            vacio=false;
            velocidad.setError(getString(R.string.ErrorVelocidadCarro));
        }if(plac.isEmpty()){
            vacio=false;
            placa.setError(getString(R.string.ErrorPlacaCarro));
        }if(pre.isEmpty()){
            vacio=false;
            precio.setError(getString(R.string.ErrorPrecioCarro));
        }

        if(marc.equals(plac)&&!marc.isEmpty()){
            marca.setError(getString(R.string.ErrorRepetirPlaca));
            vacio=false;
        }
        if(col.equals(plac)&&!col.isEmpty()){
            color.setError(getString(R.string.ErrorRepetirPlaca));
            vacio=false;
        }
        if(pre.equals(plac)&&!pre.isEmpty()){
            precio.setError(getString(R.string.ErrorRepetirPlaca));
            vacio=false;
        }
        if(velo.equals(plac)&&!velo.isEmpty()){
            velocidad.setError(getString(R.string.ErrorRepetirPlaca));
            vacio=false;
        }

        return vacio;
    }
}
