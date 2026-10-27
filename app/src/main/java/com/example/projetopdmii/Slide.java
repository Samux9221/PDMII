package com.example.projetopdmii;

public class Slide {
    private String titulo;
    private int imagem;
    private String texto;
    private String tipo;


    //construtor 2 - incluindo a descricao agora (texto)
    public Slide(String titulo, int imagem, String texto, String tipo){
        this.titulo = titulo;
        this.imagem = imagem;
        this.texto = texto;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
