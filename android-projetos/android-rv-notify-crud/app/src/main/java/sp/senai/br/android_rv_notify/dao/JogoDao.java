package sp.senai.br.android_rv_notify.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sp.senai.br.android_rv_notify.model.Jogo;

/**
 * Created by adminLocal on 10/01/2018.
 */

public class JogoDao {

    public static JogoDao manager = new JogoDao();

    // Lista aonde serão armazenados os jogos
    private List<Jogo> lista;

    private long id = 1;

    private JogoDao() {
        lista = new ArrayList<>();
        lista.add(new Jogo(id++, "StarWars", "Luta"));
        lista.add(new Jogo(id++, "Final Fantasy XII", "RPG"));
        lista.add(new Jogo(id++, "Mad Max", "Ação"));
        lista.add(new Jogo(id++, "Rambo", "Ação"));
        lista.add(new Jogo(id++, "Os Mercenários", "Ação"));
        lista.add(new Jogo(id++, "Minions", "Desenho"));
        lista.add(new Jogo(id++, "Rocky", "Ação"));
        lista.add(new Jogo(id++, "Pokemon", "Ação"));
    }

    public List<Jogo> getLista() {

        return lista;

    }

    public void remover(Jogo jogo) {
        lista.remove(jogo);
        Log.d("Deletar: ", lista.toString());
    }

    public void salvar(Jogo obj) {
        if(obj.getId() == null) {
            obj.setId(id++);
            lista.add(obj);
        } else {
            lista.set(lista.indexOf(obj), obj);
        }
        Log.d("Salvar: ", lista.toString());
    }

    public Jogo localizar(Long id) {
        return lista.get(lista.indexOf(new Jogo(id)));
    }

}
