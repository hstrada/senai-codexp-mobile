package sp.senai.br.recyclerview.view.holder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sp.senai.br.recyclerview.R;
import sp.senai.br.recyclerview.model.Filme;
import sp.senai.br.recyclerview.view.adapter.FilmeAdapter;

/**
 * Created by helena.strada on 04/01/2018.
 */

public class FilmeViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

    public final TextView nome;
    public final TextView genero;
    private Long filmeId;
    public final FilmeAdapter adapter;

    public FilmeViewHolder(final View view, final FilmeAdapter adapter) {
        super(view);
        this.adapter = adapter;

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        nome = view.findViewById(R.id.tvNome);
        genero = view.findViewById(R.id.tvGenero);
    }

    public void preencher(Filme filme) {
        filmeId = filme.getId();
        nome.setText(filme.getNome());
        genero.setText(filme.getGenero());
    }

    // método "obrigatório"
    @Override
    public void onClick(View view) {
        // Realizando um Toast (tela) para mostrar qual ID estamos selecionando
        Toast.makeText(view.getContext(), filmeId.toString(), Toast.LENGTH_SHORT).show();
        // Mostrando o mesmo ID no log
        Log.d("Filme Clicado", filmeId.toString());
    }

    @Override
    public boolean onLongClick(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.filme_options, popupMenu.getMenu());

        final Activity context = (Activity)view.getContext();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuFilmeEditar:
                        break;

                    case R.id.menuFilmeDeletar:
                        break;
                }

                return true;
            }
        });

        popupMenu.show();
        return false;
    }
}
