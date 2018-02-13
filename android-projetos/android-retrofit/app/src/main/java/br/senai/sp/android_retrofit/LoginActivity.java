package br.senai.sp.android_retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.android_retrofit.commons.AppUtils;
import br.senai.sp.android_retrofit.config.RetrofitConfig;
import br.senai.sp.android_retrofit.model.Conta;
import br.senai.sp.android_retrofit.rest.RestInterface;
import br.senai.sp.android_retrofit.views.MainActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.senai.sp.android_retrofit.commons.AppUtils.API_BASE_URL;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private String token;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etSenha = findViewById(R.id.etSenha);
        btLogin = findViewById(R.id.btLogin);

        /* final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build(); */

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        if (token != null) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // RestInterface restInterface = retrofit.create(RestInterface.class);
                Call<ResponseBody> call = new RetrofitConfig().getRestInterface().buscarLogin(new Conta(etUsuario.getText().toString(), etSenha.getText().toString()));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", response.headers().get("authorization"));
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("token", null);
//        editor.apply();
//    }
}
