package br.senai.sp.android_retrofit_sem_autenticacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.android_retrofit_sem_autenticacao.config.RetrofitConfig;
import br.senai.sp.android_retrofit_sem_autenticacao.model.CEP;
import br.senai.sp.android_retrofit_sem_autenticacao.service.HttpService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilCep;
    private Button btnBuscar;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tilCep = findViewById(R.id.tilCep);
        tvResultado = findViewById(R.id.tvResultado);
        btnBuscar = findViewById(R.id.btnBuscarCep);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // apenas configuramos, queremos agora realizar a requisição e recebermos a resposta
                Call<CEP> call = new RetrofitConfig().getCEPService().buscarCEP(tilCep.getEditText().getText().toString());
                // classe anônima do tipo callback
                call.enqueue(new Callback<CEP>() {
                    @Override
                    public void onResponse(Call<CEP> call, Response<CEP> response) {
                        if (response.isSuccessful()) {
                            CEP cep = response.body();
                            tvResultado.setText(cep.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CEP> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Não foi possível realizar a chamada.", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

//        btnBuscar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    CEP retorno = new HttpService(tilCep.getEditText().getText().toString()).execute().get();
//                    tvResultado.setText(retorno.toString());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
