package com.example.material_design;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RecylcerViewAdaptador extends RecyclerView.Adapter<RecylcerViewAdaptador.ViewHolder> {

    public List<Carro> carroLista;
    private final OnCarroClickListener clickListener;


    public RecylcerViewAdaptador(List<Carro> carroLista,OnCarroClickListener clickListener) {
        this.carroLista = carroLista;
        this.clickListener=clickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carros,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Carro p= carroLista.get(position);
        StorageReference storageReference;
        storageReference= FirebaseStorage.getInstance().getReference();

        storageReference.child(p.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });

        holder.marca.setText(carroLista.get(position).getMarca());
        holder.color.setText(carroLista.get(position).getColor());
        holder.velocidad.setText(carroLista.get(position).getVelocidad());
        holder.placa.setText(carroLista.get(position).getPlaca());
        holder.precio.setText(carroLista.get(position).getPrecio());
        holder.foto.setImageResource(carroLista.get(position).getFoto());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onCarroClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carroLista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public View v;
        private TextView marca,color,placa,precio,velocidad;
        ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v =itemView;
            marca=itemView.findViewById(R.id.marcaCarro);
            color=itemView.findViewById(R.id.colorCarro);
            precio=itemView.findViewById(R.id.precioCarro);
            velocidad=itemView.findViewById(R.id.maxVelocidadCarro);
            placa=itemView.findViewById(R.id.placaCarro);
            foto=itemView.findViewById(R.id.imgCarro);
        }
    }

    public interface OnCarroClickListener{
        void onCarroClick(Carro p);
    }

}
