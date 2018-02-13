package br.senai.sp.android_retrofit_sem_autenticacao.service;

import br.senai.sp.android_retrofit_sem_autenticacao.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Helena Strada on 12/02/2018.
 */

public interface CEPService {

    @GET("{cep}")
    Call<CEP> buscarCEP(@Path("cep") String cep);

}

 /* Criar o serviço
    Criar a requisição
    Definir o verbo HTTP(Get, Post, Delete, Put, etc...)
    Definir a URL
    Definir os parâmetros que serão enviados na requisição
    Definir o retorno */
