package com.example.projetopdmii;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
* A ideia desse metodo e MAPEAR os elementos do nosso "layout.xml"
*/
public class SlideHolder extends RecyclerView.ViewHolder {

    public TextView titulo, tipo;
    public ImageView imagem;

    //construtor dessa classe
    public SlideHolder(@NonNull View itemView) {
        super(itemView);

        titulo = itemView.findViewById(R.id.titulo);
        imagem = itemView.findViewById(R.id.imageView3);
        tipo = itemView.findViewById(R.id.tipo);
    }
}
