package sp.senai.br.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import sp.senai.br.recyclerview.dao.FilmeDao;
import sp.senai.br.recyclerview.model.Filme;
import sp.senai.br.recyclerview.view.adapter.FilmeAdapter;

public class MainActivity extends AppCompatActivity {

    private FilmeDao dao = FilmeDao.manager;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Filme> filmes = dao.getLista();

        recyclerView = findViewById(R.id.rvFilmes);

        recyclerView.setAdapter(new FilmeAdapter(filmes, this));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}
