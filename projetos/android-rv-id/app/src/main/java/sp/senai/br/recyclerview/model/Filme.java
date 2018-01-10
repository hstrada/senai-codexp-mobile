package sp.senai.br.recyclerview.model;

import android.support.annotation.NonNull;

/**
 * Created by helena.strada on 04/01/2018.
 */

public class Filme implements Comparable<Filme> {

    private Long id;
    private String nome;
    private String genero;

    public Filme(Long id, String nome, String genero) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
    }

    public Filme(Long id) {
        this.id = id;
    }

    public Filme() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int compareTo(@NonNull Filme filme) {
        return nome.toLowerCase().compareTo(filme.nome.toLowerCase());
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
