package br.senai.sp.android_livros_crud_busca.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.senai.sp.android_livros_crud_busca.R;
import br.senai.sp.android_livros_crud_busca.model.Livro;

/**
 * Created by Helena Strada on 16/03/2018.
 */

public class LivroAdapter extends BaseAdapter {

    private final List<Livro> livrosAdapter;
    private final Activity activity;
    private ArrayList<Livro> livrosLista;

    public LivroAdapter(List<Livro> livrosAdapter, Activity activity) {
        this.livrosAdapter = livrosAdapter;
        this.activity = activity;
        this.livrosLista = new ArrayList<>();
        this.livrosLista.addAll(livrosAdapter);
    }

    @Override
    public int getCount() {
        return livrosAdapter.size();
    }

    @Override
    public Object getItem(int i) {
        return livrosAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout;

        if(view == null) {
            Context ctx = viewGroup.getContext();
            LayoutInflater svc = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = new LinearLayout(ctx);
            svc.inflate(R.layout.livro_item_adapter, layout);
        } else {
            layout = (LinearLayout)view;
        }

        Livro livro = livrosAdapter.get(i);

        // buscando as referências das views
        TextView nomeLivro = layout.findViewById(R.id.tvNomeLivro);
        TextView autorLivro = layout.findViewById(R.id.tvAutorLivro);

        // "populando" as views
        nomeLivro.setText(livro.getNome());
        autorLivro.setText(livro.getAutor());

        return layout;
    }

    // filtrando por nome
    public void filtrarPorNome(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        livrosAdapter.clear();
        if (charText.length() == 0) {
            livrosAdapter.addAll(livrosLista);
        } else {
            for (Livro l : livrosLista) {
                if (l.getNome().toLowerCase(Locale.getDefault()).contains(charText)) {
                    livrosAdapter.add(l);
                }
            }
        }
        notifyDataSetChanged();
    }
}
