package com.example.projetopdmii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
* CLASSE ADAPTER
* Tem como funcao juntar tudo: Activity3, layout.xml e SlideHolder
*/
public class SlideAdapter extends RecyclerView.Adapter<SlideHolder> {

    private ArrayList<Slide> lista; //objeto lista do tipo da classe Slide (nome, imagem e texto, lembra?)

    public SlideAdapter(ArrayList<Slide> lista){
        this.lista = lista;
    }


    //ele precisa de um view (qualquer objeto do layout), então já vinculamos nosso layout que criamos
    @NonNull
    @Override
    public SlideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //aqui estamos definindo o que iremos inflar, passando como contexto a classe layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);

        return new SlideHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideHolder holder, int position) {
        holder.titulo.setText(lista.get(position).getTitulo());
        holder.texto.setText(lista.get(position).getTexto());
        holder.imagem.setImageResource(lista.get(position).getImagem());
        holder.tipo.setText(lista.get(position).getTipo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}