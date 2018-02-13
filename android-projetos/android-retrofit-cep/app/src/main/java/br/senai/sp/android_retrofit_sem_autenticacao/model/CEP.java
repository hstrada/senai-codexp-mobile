package br.senai.sp.android_retrofit_sem_autenticacao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Helena Strada on 12/02/2018.
 */

@JsonIgnoreProperties({"estado_info", "cidade_info"})
public class CEP {

    private String bairro;
    private String cidade;
    private String logradouro;
    private String cep;
    private String estado;

    @Override
    public String toString() {
        return "CEP{" +
                "bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", cep='" + cep + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
