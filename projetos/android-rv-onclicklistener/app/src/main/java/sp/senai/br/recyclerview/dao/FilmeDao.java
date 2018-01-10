package sp.senai.br.recyclerview.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sp.senai.br.recyclerview.model.Filme;

/**
 * Created by helena.strada on 04/01/2018.
 */

public class FilmeDao {

    public static FilmeDao manager = new FilmeDao();

    // Lista aonde serão armazenados os filmes
    private List<Filme> lista;

    // Geração do id para cada novo filme. No nosso caso, teremos somente uma lista para exemplo.
    private long id = 10;

    private FilmeDao() {
        lista = new ArrayList<>();
        lista.add(new Filme((long) 1, "StarWars", "Luta"));
        lista.add(new Filme((long) 3, "Final Fantasy XII", "RPG"));
        lista.add(new Filme((long) 5, "Final Fantasy XII", "RPG"));
    }

    public List<Filme> getLista() {

        Collections.sort(lista);
        return Collections.unmodifiableList(lista);

    }

}
