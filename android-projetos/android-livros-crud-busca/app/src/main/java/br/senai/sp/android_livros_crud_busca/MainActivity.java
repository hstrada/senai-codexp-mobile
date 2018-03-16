package br.senai.sp.android_livros_crud_busca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.android_livros_crud_busca.adapter.LivroAdapter;
import br.senai.sp.android_livros_crud_busca.model.Livro;

public class MainActivity extends AppCompatActivity {

    private ListView lvLivros;
    private SearchView svLivros;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLivros = findViewById(R.id.lvLivros);
        svLivros = findViewById(R.id.svLivros); // inititate a search view

        CharSequence query = svLivros.getQuery(); // get the query string currently in the text field

        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro("Nome A", "Autor A"));
        livros.add(new Livro("Nome B", "Autor B"));
        livros.add(new Livro("Felipe", "Melhor Professor"));
        livros.add(new Livro("Thales", "Panda"));

        livroAdapter = new LivroAdapter(livros, this);
        lvLivros.setAdapter(livroAdapter);

        svLivros.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                // recebo a String que quero buscar
                String livroBuscado = s;
                // coloco um filtro no próprio adapter
                livroAdapter.filtrarPorNome(livroBuscado);
                return false;
            }
        });


    }

}
