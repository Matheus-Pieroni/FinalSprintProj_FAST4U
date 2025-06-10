package com.example.finalsprintproj_fast4u;

import java.io.Serializable;

public class CardInfo implements Serializable {
    private String nomeDoCartao;
    private int numDoCartao;
    private int[] validadeDoCartao = new int[3]; //0, 1, 2... <<- Não se esqueça!!!

    //Construtor vazio
    public CardInfo() {
    }

    //Construtor padrão
    public CardInfo(String nomeDoCartao, int numDoCartao, int[] validadeDoCartao) {
        this.nomeDoCartao = nomeDoCartao;
        this.numDoCartao = numDoCartao;
        this.validadeDoCartao = validadeDoCartao;
    }

    public void setNomeDoCartao(String nomeDoCartao) {
        this.nomeDoCartao = nomeDoCartao;
    }
    public void setNumDoCartao(int numDoCartao) {
        this.numDoCartao = numDoCartao;
    }
    public void setValidadeDoCartao(int dia, int mes, int ano) {
        this.validadeDoCartao[0] = dia;
        this.validadeDoCartao[1] = mes;
        this.validadeDoCartao[2] = ano;
    }
    public String getNomeDoCartao() {
        return nomeDoCartao;
    }
    public int getNumDoCartao() {
        return numDoCartao;
    }

    public int[] getValidadeDoCartao() {
        return validadeDoCartao;
    }
}
