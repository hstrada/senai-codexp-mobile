package sp.senai.br.android_rv_notify.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sp.senai.br.android_rv_notify.R;
import sp.senai.br.android_rv_notify.model.Jogo;
import sp.senai.br.android_rv_notify.view.holder.JogoViewHolder;

/**
 * Created by adminLocal on 10/01/2018.
 */

public class JogoAdapter extends RecyclerView.Adapter {

    private List<Jogo> jogos;
    private Context context;

    public JogoAdapter(List<Jogo> jogos, Context context) {
        this.jogos = jogos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.jogo_item_lista, parent, false);
        JogoViewHolder holder = new JogoViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        JogoViewHolder viewHolder = (JogoViewHolder) holder;

        Jogo jogo = jogos.get(position);

       viewHolder.preencher(jogo);

    }

    @Override
    public int getItemCount() {
        return jogos.size();
    }

    public void remove() {
        notifyDataSetChanged();
    }

}
