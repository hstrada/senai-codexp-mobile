package br.senai.sp.android_retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrador on 10/02/2018.
 */

public class Cliente {

    @SerializedName("id")
    private Long id;

    @SerializedName("nomeFantasia")
    private String nomeFantasia;

    @SerializedName("razaoSocial")
    private String razaoSocial;

    public Cliente(String nomeFantasia, String razaoSocial) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public Cliente() {

    }

    public Cliente(Long id, String nomeFantasia, String razaoSocial) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                '}';
    }
}
