package br.senai.sp.android_retrofit_sem_autenticacao.config;

import br.senai.sp.android_retrofit_sem_autenticacao.service.CEPService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.senai.sp.android_retrofit_sem_autenticacao.utils.AppUtils.BASE_URL;

/**
 * Created by Helena Strada on 12/02/2018.
 */

/*
*
* Essa classe ficará responsável por configurar e instanciar o Retrofit
*
* */
public class RetrofitConfig {

    // atributo
    private final Retrofit retrofit;

    // Nossa configuração será feita no construtor
    public RetrofitConfig() {

        // Precisamos construir um objeto do tipo retrofit
        this.retrofit = new Retrofit.Builder()
                    // definimos a url base da nossa aplicação
                    .baseUrl(BASE_URL)
                    // precisamos transformar a nossa resposta que vem em JSON para String
                    .addConverterFactory(JacksonConverterFactory.create())
                    // precisamos de fato criá-lo
                    .build();

    }

    public CEPService getCEPService() {
        return this.retrofit.create(CEPService.class);
    }

}
