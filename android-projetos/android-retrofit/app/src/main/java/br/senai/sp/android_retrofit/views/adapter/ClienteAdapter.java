package br.senai.sp.android_retrofit.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.android_retrofit.R;
import br.senai.sp.android_retrofit.model.Cliente;
import br.senai.sp.android_retrofit.views.holder.ClienteViewHolder;

/**
 * Created by Helena Strada on 13/02/2018.
 */

public class ClienteAdapter extends RecyclerView.Adapter{

    private List<Cliente> clientes;
    private Context context;

    public ClienteAdapter(List<Cliente> clientes, Context context) {
        this.clientes = clientes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cliente_item_lista, parent, false);
        ClienteViewHolder holder = new ClienteViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ClienteViewHolder viewHolder = (ClienteViewHolder) holder;

        Cliente cliente  = clientes.get(position);

        ((ClienteViewHolder) holder).preencher(cliente);

    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
