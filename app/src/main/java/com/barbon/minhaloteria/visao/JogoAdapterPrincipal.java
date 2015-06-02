package com.barbon.minhaloteria.visao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Barbon on 01/06/2015.
 */
public class JogoAdapterPrincipal extends BaseAdapter {

    private Context context;
    private List<JogoPrincipal> jogoPrincipals;

    public JogoAdapterPrincipal(Context context, List<JogoPrincipal> jogoPrincipals){
        this.context = context;
        this.jogoPrincipals = jogoPrincipals;
    }

    @Override
    public int getCount() {
        return jogoPrincipals.size();
    }

    @Override
    public Object getItem(int position) {
        return jogoPrincipals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogoPrincipals.get(position).jogo.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JogoPrincipal jogoPrincipal = jogoPrincipals.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        return null;
    }

    static class ViewHolder {
        ImageView logoLoteria;
        TextView idLoteria;
        TextView numeros;
        TextView ultimaAposta;
        TextView ultimoSorteio;
    }
}
