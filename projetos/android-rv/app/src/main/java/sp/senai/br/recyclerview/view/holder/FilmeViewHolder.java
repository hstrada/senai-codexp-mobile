package sp.senai.br.recyclerview.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sp.senai.br.recyclerview.R;
import sp.senai.br.recyclerview.model.Filme;
import sp.senai.br.recyclerview.view.adapter.FilmeAdapter;

/**
 * Created by helena.strada on 04/01/2018.
 */

public class FilmeViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;
    public final TextView genero;
    private Long filmeId;
    public final FilmeAdapter adapter;

    public FilmeViewHolder(final View view, final FilmeAdapter adapter) {
        super(view);
        this.adapter = adapter;
        nome = view.findViewById(R.id.tvNome);
        genero = view.findViewById(R.id.tvGenero);
    }

    public void preencher(Filme filme) {
        filmeId = filme.getId();
        nome.setText(filme.getNome());
        genero.setText(filme.getGenero());
    }
}
