package br.senai.sp.android_retrofit.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import br.senai.sp.android_retrofit.R;
import br.senai.sp.android_retrofit.commons.AppUtils;
import br.senai.sp.android_retrofit.config.RetrofitConfig;
import br.senai.sp.android_retrofit.model.Cliente;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.senai.sp.android_retrofit.commons.AppUtils.CODE_CLIENTE;

public class EditActivity extends AppCompatActivity {

    private TextInputLayout tilNomeFantasia;
    private TextInputLayout tilRazaoSocial;
    private TextInputEditText tietNomeFantasia;
    private TextInputEditText tietRazaoSocial;
    private Button btnSalvar;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tilNomeFantasia = findViewById(R.id.tilNomeFantasia);
        tilRazaoSocial = findViewById(R.id.tilRazaoSocial);
        tietNomeFantasia = findViewById(R.id.tietNomeFantasia);
        tietRazaoSocial = findViewById(R.id.tietRazaoSocial);
        btnSalvar = findViewById(R.id.btnSalvar);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        final Bundle bundle = getIntent().getExtras();
        final Long clienteId = (bundle != null) ? bundle.getLong("clienteId") : null;

        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder b = chain.request().newBuilder();
                b.addHeader("Accept", "application/json");
                b.addHeader("Authorization", token);
                return chain.proceed(b.build());
            }
        }).build();

        carregarCliente(okHttpClient, clienteId);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (clienteId == null) {
                    Call<Cliente> call = new RetrofitConfig(okHttpClient).getRestInterface().salvarCliente(
                            new Cliente(tilNomeFantasia.getEditText().getText().toString()
                                    ,tilRazaoSocial.getEditText().getText().toString()));

                    call.enqueue(new Callback<Cliente>() {
                        @Override
                        public void onResponse(Call<Cliente> call, Response<Cliente> response) {

                            if (response.isSuccessful()) {
                                // Toast.makeText(getApplicationContext(), "Salvo com Sucesso.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent();
                                setResult(CODE_CLIENTE, intent);
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<Cliente> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Call<Cliente> call = new RetrofitConfig(okHttpClient).getRestInterface().atualizarCliente(new Cliente
                            (clienteId
                            , tilNomeFantasia.getEditText().getText().toString()
                            ,tilRazaoSocial.getEditText().getText().toString()));

                    call.enqueue(new Callback<Cliente>() {
                        @Override
                        public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent();
                                setResult(CODE_CLIENTE, intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Cliente> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }


            }
        });


    }

    private void carregarCliente(OkHttpClient client, Long clienteId) {

        // se eu não possuo um id para o meu cliente, crio um novo
        if (clienteId == null) {
            Cliente cliente = new Cliente();
        }
        // se eu possuo um id para o cliente, carrego os dados dele
        else {
            Call<Cliente> call = new RetrofitConfig(client).getRestInterface().buscarCliente(clienteId);
            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    if (response.isSuccessful()) {
                        // se a minha resposta for com sucesso, carrego os dados dele de acordo com os dados que eu recebi da API
                        Cliente cliente = response.body();
                        tietNomeFantasia.setText(cliente.getNomeFantasia());
                        tietRazaoSocial.setText(cliente.getRazaoSocial());
                        // Coloco um log apenas para informar o cliente que eu carreguei
                        Log.d("Cliente: ", cliente.toString());
                    }
                }

                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    // caso algo dê errado, informo uma mensagem de erro para o cliente e faço um log para verificar melhor o erro no console
                    Toast.makeText(getApplicationContext(), "Erro ao Carregar Cliente.", Toast.LENGTH_LONG).show();
                    Log.d("Erro EditActivity: ", t.getMessage());
                }
            });

        }

    }
}
