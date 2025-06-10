package com.example.finalsprintproj_fast4u;

import android.net.Uri;

import java.io.Serializable;

public class UserInfo implements Serializable {

    //Este arquivo / Classe só está aqui para completar a implementação e manter tudo de acordo com o diagrama de classes. :)
    private String email;
    private String popName;
    private Uri photoUrl;
    private String userAddress;
    private String prefFood;
    private String buyMethod;

    //Construtor genérico do Objeto cliente / Data do Usuário
    public UserInfo(String email, String popName, Uri photoUrl, String prefFood, String buyMethod, String userAddress) {
        this.email = email;
        this.popName = popName;
        this.photoUrl = photoUrl;
        this.prefFood = prefFood;
        this.buyMethod = buyMethod;
        this.userAddress = userAddress;
    }

    //Construtor vazio  :O
    public UserInfo() {
    }

    //O nome de alguns métodos foi adaptado para se adequar melhor ao projeto final, correspondendo ao restante do projeto.
    public void setNome(String nome) {
        this.popName = nome;
    }
    public String getNome() {
        return this.popName;
    }
    public void setUserAddress(String userAddress) {this.userAddress = userAddress; }
    public String getUserAddress() {return this.userAddress; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setPrefFood(String prefFood) {
        this.prefFood = prefFood;
    }
    public String getPrefFood() {
        return this.prefFood;
    }
    public void setBuyMethod(String buyMethod) {
        this.buyMethod = buyMethod;
    }
    public String getBuyMethod() {
        return this.buyMethod;
    }
    /*

    Nenhum uso real para estes aqui.

                    |
                    V

     public void setPhotoUri(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    */
}
