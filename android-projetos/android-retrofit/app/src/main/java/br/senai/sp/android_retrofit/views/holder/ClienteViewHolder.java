package br.senai.sp.android_retrofit.views.holder;

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

import br.senai.sp.android_retrofit.R;
import br.senai.sp.android_retrofit.model.Cliente;
import br.senai.sp.android_retrofit.views.EditActivity;
import br.senai.sp.android_retrofit.views.adapter.ClienteAdapter;

import static br.senai.sp.android_retrofit.commons.AppUtils.CODE_CLIENTE;

/**
 * Created by Helena Strada on 13/02/2018.
 */

public class ClienteViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    public final TextView nomeFantasia;
    public final TextView razaoSocial;
    private Long clienteId;
    public final ClienteAdapter adapter;

    public ClienteViewHolder(final View view, final ClienteAdapter adapter) {
        super(view);
        this.adapter = adapter;

        view.setOnClickListener(this);

        nomeFantasia = view.findViewById(R.id.tvNomeFantasia);
        razaoSocial = view.findViewById(R.id.tvRazaoSocial);
    }

    public void preencher(Cliente cliente) {
        clienteId = cliente.getId();
        nomeFantasia.setText(cliente.getNomeFantasia());
        razaoSocial.setText(cliente.getRazaoSocial());
    }

    // método "obrigatório"
    @Override
    public void onClick(View view) {
        // Realizando um Toast (tela) para mostrar qual ID estamos selecionando
        // Toast.makeText(view.getContext(), clienteId.toString(), Toast.LENGTH_SHORT).show();
        // Mostrando o mesmo ID no log
        Log.d("Cliente: ", clienteId.toString());
        Intent intent = new Intent(view.getContext(), EditActivity.class);
        intent.putExtra("clienteId", clienteId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);

    }

}
