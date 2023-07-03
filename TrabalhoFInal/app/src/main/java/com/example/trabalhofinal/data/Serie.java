package com.example.trabalhofinal.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Serie implements Serializable {
    private int id;
    private String nome;
    private String genero;
    private int quantidadeTemporadas;


    public Serie(String nome, String genero, int quantidadeTemporadas) {
        this.nome = nome;
        this.genero = genero;
        this.quantidadeTemporadas = quantidadeTemporadas;
    }

    public Serie() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidadeTemporadas() {
        return quantidadeTemporadas;
    }

    public void setQuantidadeTemporadas(int quantidadeTemporadas) {
        this.quantidadeTemporadas = quantidadeTemporadas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
