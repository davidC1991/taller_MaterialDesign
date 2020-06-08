package com.example.material_design;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleCarro extends AppCompatActivity {
    Carro p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);

        final ImageView foto;
        TextView marca,color,precio,velocidad,placa;
        Bundle bundle;
        Intent intent;
        String marc, col, prec,veloc,plac,id;
        int fot;
        StorageReference storageReference;

        foto= findViewById(R.id.imgFotoDetalle);
        marca=findViewById(R.id.lblMarcaDetalle);
        color=findViewById(R.id.lblColorDetalle);
        placa=findViewById(R.id.lblPlacaDetalle);
        velocidad= findViewById(R.id.lblVelocidadDetalle);
        precio=findViewById(R.id.lblPrecioDetalle);

        intent= getIntent();
        bundle= intent.getBundleExtra("datos");

        id=bundle.getString("id");
        marc=bundle.getString("marca");
        col=bundle.getString("color");
        prec=bundle.getString("precio");
        veloc=bundle.getString("velocidad");
        plac=bundle.getString("placa");

        storageReference= FirebaseStorage.getInstance().getReference();
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        marca.setText(marc);
        color.setText(col);
        placa.setText(plac);
        precio.setText(prec);
        velocidad.setText(veloc);

        p=new Carro(marc,col,plac,prec,veloc,fot=0,id);



    }

    public void onBackPressed(){
        //finish();
        Intent intent =new Intent(DetalleCarro.this,MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.TituloElimnarCarro));
        builder.setMessage(getString(R.string.pregunta_eliminarCarro));
        positivo=getString(R.string.opcion_si);
        negativo = getString(R.string.opcion_no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                p.elminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog= builder.create();
        dialog.show();
    }
}
