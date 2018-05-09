package br.senai.sp.android_retrofit_sem_autenticacao.service;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.android_retrofit_sem_autenticacao.model.CEP;
import br.senai.sp.android_retrofit_sem_autenticacao.utils.AppUtils;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by matheus on 13/11/17.
 */

public class HttpService extends AsyncTask<Void, Void, CEP> {

    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.cep != null && this.cep.length() == 8) {
            try {
                URL url = new URL(AppUtils.BASE_URL + cep);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(resposta.toString(), CEP.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
