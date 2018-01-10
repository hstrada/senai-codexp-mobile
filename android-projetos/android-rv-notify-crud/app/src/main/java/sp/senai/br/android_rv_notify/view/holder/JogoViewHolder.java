package sp.senai.br.android_rv_notify.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sp.senai.br.android_rv_notify.EditActivity;
import sp.senai.br.android_rv_notify.MainActivity;
import sp.senai.br.android_rv_notify.R;
import sp.senai.br.android_rv_notify.dao.JogoDao;
import sp.senai.br.android_rv_notify.model.Jogo;
import sp.senai.br.android_rv_notify.view.adapter.JogoAdapter;

/**
 * Created by adminLocal on 10/01/2018.
 */

public class JogoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public final TextView nome;
    public final TextView fabricante;
    private Long jogoId;
    public final JogoAdapter adapter;
    private JogoDao dao = JogoDao.manager;

    public JogoViewHolder(final View view, final JogoAdapter adapter) {
        super(view);
        this.adapter = adapter;

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        nome = view.findViewById(R.id.tvNome);
        fabricante = view.findViewById(R.id.tvFabricante);
    }

    public void preencher(Jogo jogo) {
        jogoId = jogo.getId();
        nome.setText(jogo.getNome());
        fabricante.setText(jogo.getFabricante());
    }

    // método "obrigatório"
    @Override
    public void onClick(View view) {
        final Activity context = (Activity)view.getContext();
        final Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("jogoId", jogoId);
        context.startActivityForResult(intent, MainActivity.EDIT_ACTION);
    }

    @Override
    public boolean onLongClick(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.jogo_options, popupMenu.getMenu());

        final Activity context = (Activity)view.getContext();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuJogoEditar:
                        final Intent intent = new Intent(context, EditActivity.class);
                        intent.putExtra("jogoId", jogoId);
                        context.startActivityForResult(intent, MainActivity.EDIT_ACTION);
                        break;

                    case R.id.menuJogoDeletar:
                        dao.remover(new Jogo(jogoId));
                        remover();
                        break;
                }

                return true;
            }
        });

        popupMenu.show();
        return false;
    }

    public void remover(){
        adapter.remove();
    }
}
