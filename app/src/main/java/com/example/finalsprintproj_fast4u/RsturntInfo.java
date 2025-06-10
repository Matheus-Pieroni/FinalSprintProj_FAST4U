package com.example.finalsprintproj_fast4u;

import java.io.Serializable;

public class RsturntInfo implements Serializable {
    private String nome;
    private tipoComida tipoComida;

    public RsturntInfo() {
        //Construtor Vazio
    }
    public RsturntInfo(String nome, tipoComida tipoComida) {
        this.nome = nome;
        this.tipoComida = tipoComida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTipoComida(tipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }
    public String getNome() {
        return nome;
    }
    public tipoComida getTipoComida() {
        return tipoComida;
    }
}
