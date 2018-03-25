package com.example.administrador.curso3_tarea4.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrador.curso3_tarea4.R;
import com.example.administrador.curso3_tarea4.pojo.Mascota;
import java.util.ArrayList;

/**
 * Created by administrador on 18/05/17.
 */

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;


    //******** Constructor *******
    public PerfilAdaptador (ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // Método que va a inflar el layout y lo pasara al ViewHolder para que obtenga los views
    @Override
    public PerfilAdaptador.PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new PerfilAdaptador.PerfilViewHolder(v);
    }


    // Se setean los datos de la clase MascotaViewHolder con los datos de la lista recibida
    @Override
    public void onBindViewHolder(final PerfilAdaptador.PerfilViewHolder mascotaViewHolder, int position){
        final Mascota mascota = mascotas.get(position); //Obtiene todos los datos de la mascota en la posición position
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());// Seteo el cardView con la foto recibida del ArrayList
        mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getNumLinkes()));// Seteo el Número de likes del cardView
        mascotaViewHolder.llCardViewPerfil.setBackgroundResource(mascota.getColorFondo()); // Establece el color de fondo

    }

    @Override
    public int getItemCount(){
        return mascotas.size();
    }

    //**********  Clase interna MascotaViewHolder *****************
    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNumLikes;
        //agregado
        private LinearLayout llCardViewPerfil;

        // Constructor
        public PerfilViewHolder(View itemView){
            super(itemView);
            // Cargo todos las vistas del cardview
            this.imgFoto    = (ImageView) itemView.findViewById(R.id.imgFoto);;
            this.tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);
            // Agregado
            this.llCardViewPerfil = (LinearLayout) itemView.findViewById(R.id.llCardViewPerfil);
        }

    }
}
