package com.example.material_design;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecylcerViewAdaptador extends RecyclerView.Adapter<RecylcerViewAdaptador.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView marca,color,placa,precio,velocidad;
        ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marca=itemView.findViewById(R.id.marcaCarro);
            color=itemView.findViewById(R.id.colorCarro);
            precio=itemView.findViewById(R.id.precioCarro);
            velocidad=itemView.findViewById(R.id.maxVelocidadCarro);
            placa=itemView.findViewById(R.id.placaCarro);
            foto=itemView.findViewById(R.id.imgCarro);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carros,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.marca.setText(carroLista.get(position).getMarca());
        holder.color.setText(carroLista.get(position).getColor());
        holder.velocidad.setText(Integer.toString(carroLista.get(position).getVelocidad()));
        holder.placa.setText(carroLista.get(position).getPlaca());
        holder.precio.setText(Integer.toString(carroLista.get(position).getPrecio()));
        holder.foto.setImageResource(carroLista.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return carroLista.size();
    }


    public List<carro> carroLista;

    public RecylcerViewAdaptador(List<carro> carroLista) {
        this.carroLista = carroLista;
    }

}
