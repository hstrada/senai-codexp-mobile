package sp.senai.br.android_rv_notify.dao;

import android.util.Log;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collection;
import java.util.List;

import sp.senai.br.android_rv_notify.App;
import sp.senai.br.android_rv_notify.lib.JSONParser;
import sp.senai.br.android_rv_notify.model.Jogo;

/**
 * Created by adminLocal on 10/01/2018.
 */
public class JogoDao {

    public static JogoDao instance = new JogoDao();

    private final String url = "http://192.168.0.4:8080/";

    private JogoDao() {

    }

    public void salvar(Jogo jogo) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(jogo);

            if (jogo.getId() == null) {
                // incluir
                new JSONParser.Incluir(url + "jogos/", json, new JSONParser.LocationAndDataCallBack() {
                    @Override
                    public void setResponse(int code, String location, String json) {
                        if(code != 200)
                            Toast.makeText(App.context, "Falha em salvar o Jogo", Toast.LENGTH_LONG).show();
                    }
                }).execute();
            } else {
                // atualizar
                new JSONParser.Alterar(url + "jogos/" + jogo.getId(), json, new JSONParser.LocationCallBack() {
                    @Override
                    public void setResponse(int code, String location) {
                        if(code != 200)
                            Toast.makeText(App.context, "Falha em alterar o Jogo", Toast.LENGTH_LONG).show();
                    }
                }).execute();
            }
        } catch (Exception ex) {
            Toast.makeText(App.context, "Falha no acesso ao Servidor", Toast.LENGTH_LONG).show();
        }

    }

    public List<Jogo> getLista() {
        List<Jogo> jogos = new ArrayList<>();

        try {
             String json = new JSONParser.Consultar(url + "jogos/" , new JSONParser.DataCallBack() {
                @Override
                public void setResponse(int code, String json) {
                    if(code != 200)
                        Toast.makeText(App.context, "Falha ao consultar os Dados", Toast.LENGTH_LONG).show();
                }
            }).execute().get();
            ObjectMapper mapper = new ObjectMapper();
            jogos = new ArrayList(Arrays.asList(mapper.readValue(new StringReader(json), Jogo[].class)));
        } catch (Exception ex) {
            Log.d("Erro Servidor", ex.getMessage());
            Toast.makeText(App.context, "Falha no acesso ao Servidor", Toast.LENGTH_LONG).show();
        }

        return jogos;

    }

    public Jogo localizar(Long id) {
        try {
            String json = new JSONParser.Consultar(url + "jogos/" + id, new JSONParser.DataCallBack() {
                @Override
                public void setResponse(int code, String json) {
                    if(code != 200)
                        Toast.makeText(App.context, "Falha em localizar o Jogo", Toast.LENGTH_LONG).show();
                    else if (code == 404)
                        Toast.makeText(App.context, "Falha em localizar o Jogo", Toast.LENGTH_LONG).show();
                }
            }).execute().get();

            Jogo jogo = null;
            if(!json.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                jogo = mapper.readValue(new StringReader(json), Jogo.class);
            } else {
                Toast.makeText(App.context, "Falha ao Localizar", Toast.LENGTH_LONG).show();
                return null;
            }

            return jogo;
        } catch (Exception ex) {
            Log.d("localizar", ex.getMessage());
            Toast.makeText(App.context, "Falha no acesso ao Servidor", Toast.LENGTH_LONG).show();
        }

        return null;
    }

    public void remover(Jogo jogo) {
        try {
            new JSONParser.Remover(url + "jogos/" + jogo.getId(), new JSONParser.ResponseCodeCallBack() {
                @Override
                public void setResponse(int code) {
                    if(code != 204)
                        Toast.makeText(App.context, "Falha ao excluir o Jogo", Toast.LENGTH_LONG).show();
                }
            }).execute();
        } catch (Exception ex) {
            Toast.makeText(App.context, "Falha no acesso ao Servidor", Toast.LENGTH_LONG).show();
        }

    }

}

