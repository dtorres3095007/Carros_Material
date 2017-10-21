package com.example.android.carros_material;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */

public class AdaptadorCarro extends RecyclerView.Adapter<AdaptadorCarro.CarrosViewHolder> {
    private ArrayList<Carro> carros;
    private Resources res;
    private OnCarroClickListener clickListener;

    public AdaptadorCarro(Context contexto, ArrayList<Carro> carro, OnCarroClickListener clickListener){
        this.carros =carro;
        res = contexto.getResources();
        this.clickListener=clickListener;
    }

    @Override
    public CarrosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_carro,parent,false);
        return new CarrosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarrosViewHolder holder, int position) {

        String [] modelo_string = res.getStringArray(R.array.array_madelo);
        String [] marca_string = res.getStringArray(R.array.array_marca);
        String [] color_string = res.getStringArray(R.array.array_color);
        final Carro p = carros.get(position);
        holder.foto.setImageDrawable(ResourcesCompat.getDrawable(res,p.getFoto(),null));
        holder.marca.setText(marca_string[p.getMarca()]);
        holder.modelo.setText(modelo_string[p.getModelo()]);
        holder.precio.setText(p.getPrecio()+"");
        holder.placa.setText(p.getPlaca());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onCarroClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarrosViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView marca;
        private TextView modelo;
        private TextView precio;
        private TextView placa;
        private View v;

        public CarrosViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = (ImageView)itemView.findViewById(R.id.imgFoto);
            marca = (TextView)itemView.findViewById(R.id.lblmarca);
            modelo = (TextView)itemView.findViewById(R.id.lblmodelo);
            precio =(TextView)itemView.findViewById(R.id.lblprecio);
            placa =(TextView)itemView.findViewById(R.id.lblplaca);
        }
    }

    public interface OnCarroClickListener {
        void onCarroClick(Carro p);
    }
}
