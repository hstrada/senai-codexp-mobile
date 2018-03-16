package br.senai.sp.android_livros_crud_busca.model;

import java.util.Arrays;

/**
 * Created by Helena Strada on 16/03/2018.
 */

public class Livro {

    private Long id;
    private String nome;
    private byte[] capa;
    private String autor;

    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public Livro(Long id, String nome, byte[] capa, String autor) {
        this.id = id;
        this.nome = nome;
        this.capa = capa;
        this.autor = autor;
    }

    public Livro() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        if (id != null ? !id.equals(livro.id) : livro.id != null) return false;
        if (nome != null ? !nome.equals(livro.nome) : livro.nome != null) return false;
        if (!Arrays.equals(capa, livro.capa)) return false;
        return autor != null ? autor.equals(livro.autor) : livro.autor == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(capa);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capa=" + Arrays.toString(capa) +
                ", autor='" + autor + '\'' +
                '}';
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

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
