package br.senai.sp.android_retrofit.rest;

import java.util.List;

import br.senai.sp.android_retrofit.model.Cliente;
import br.senai.sp.android_retrofit.model.Conta;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Helena Strada on 10/02/2018.
 */

public interface RestInterface {

    @POST("login")
    Call<ResponseBody> buscarLogin(@Body Conta conta);

    @GET("clientes")
    Call<List<Cliente>> listarClientes();

    @POST("clientes")
    Call<Cliente> salvarCliente(@Body Cliente cliente);

    @PUT("clientes")
    Call<Cliente> atualizarCliente(@Body Cliente cliente);

    @GET("clientes/{id}")
    Call<Cliente> buscarCliente(@Path("id") Long id);

}
