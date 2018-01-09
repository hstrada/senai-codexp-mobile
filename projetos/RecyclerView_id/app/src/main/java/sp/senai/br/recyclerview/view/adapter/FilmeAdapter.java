package sp.senai.br.recyclerview.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sp.senai.br.recyclerview.R;
import sp.senai.br.recyclerview.model.Filme;
import sp.senai.br.recyclerview.view.holder.FilmeViewHolder;

/**
 * Created by helena.strada on 04/01/2018.
 */

public class FilmeAdapter extends RecyclerView.Adapter {

    private List<Filme> filmes;
    private Context context;

    public FilmeAdapter(List<Filme> filmes, Context context) {
        this.filmes = filmes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.filme_item_lista, parent, false);
        FilmeViewHolder holder = new FilmeViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FilmeViewHolder viewHolder = (FilmeViewHolder) holder;

        Filme filme  = filmes.get(position);

        ((FilmeViewHolder) holder).preencher(filme);

    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }
}
