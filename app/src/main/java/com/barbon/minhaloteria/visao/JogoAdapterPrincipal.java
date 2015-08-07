package com.barbon.minhaloteria.visao;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.barbon.minhaloteria.R;
import com.barbon.minhaloteria.modelo.JogoConcursoSorteio;
import com.barbon.minhaloteria.modelo.NumeroJogado;

import java.util.List;

/**
 * Created by Barbon on 01/06/2015.
 */
public class JogoAdapterPrincipal extends BaseAdapter {

    private Context context;
    private List<JogoPrincipal> jogoPrincipals;
    private ViewHolder holder;

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

        if (convertView == null){
            convertView = inflater.inflate(R.layout.jogos_detalhes, null);
            holder = new ViewHolder();
            holder.idLoteria = (TextView) convertView.findViewById(R.id.id_jogo);
            holder.logoLoteria = (ImageView) convertView.findViewById(R.id.logoLoteria);
            holder.numeros = (TextView) convertView.findViewById(R.id.numeros);
            holder.ultimaAposta = (TextView) convertView.findViewById(R.id.lblUltimaAposta);
            holder.ultimaAposta1 = (TextView) convertView.findViewById(R.id.lblUltimaAposta1);
            holder.ultimoSorteio = (TextView) convertView.findViewById((R.id.lblUltimoSorteio));
            holder.ultimoSorteio1 = (TextView) convertView.findViewById(R.id.lblUltimoSorteio1);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.idLoteria.setText(jogoPrincipal.jogo.getDescricao() + (jogoPrincipal.jogo.isJogoPermanente()? " (" + context.getResources().getString(R.string.permanente) + ")": ""));
        holder.logoLoteria.setImageResource(jogoPrincipal.jogo.getLoteria().getImagem());
        holder.numeros.setText(definirNumeros(jogoPrincipal));

        if (jogoPrincipal.ultimaAposta == null){

            holder.ultimaAposta.setVisibility(View.GONE);
            holder.ultimaAposta1.setVisibility(View.GONE);
            holder.ultimoSorteio.setVisibility(View.GONE);
            holder.ultimoSorteio1.setVisibility(View.GONE);

        }else if (jogoPrincipal.ultimoSorteio == null){

            holder.ultimaAposta1.setText(R.string.concurso + " " + jogoPrincipal.ultimaAposta.getNumero());
            holder.ultimoSorteio.setVisibility(View.GONE);
            holder.ultimoSorteio1.setVisibility(View.GONE);

        }else{

            holder.ultimaAposta1.setText(R.string.concurso + " " + jogoPrincipal.ultimaAposta.getNumero());
            holder.ultimoSorteio1.setText(R.string.concurso + " " + jogoPrincipal.ultimoSorteio.getConcurso().getNumero() +
                                          "(" + jogoPrincipal.ultimoSorteio.getConcurso().getData() + ") " +
                                          definirAcertos(jogoPrincipal));

        }

        return convertView;
    }

    private static String definirNumeros(JogoPrincipal jogoPrincipal){

        StringBuilder stringBuilder = new StringBuilder();

        for (NumeroJogado nj: jogoPrincipal.jogo.getNumerosJogados()){
            stringBuilder.append(nj.getNumero() + " ");
        }

        return stringBuilder.toString().trim();
    }

    private static String definirAcertos(JogoPrincipal jogoPrincipal){

        StringBuilder stringBuilder = new StringBuilder();

        for (JogoConcursoSorteio jcs: jogoPrincipal.ultimoSorteio.getJogoConcursoSorteios()){
            stringBuilder.append(jcs.getQtdeAcerto() + " " + R.string.acertos + "  ");
        }

        return stringBuilder.toString().trim();
    }

    static class ViewHolder {
        ImageView logoLoteria;
        TextView idLoteria;
        TextView numeros;
        TextView ultimaAposta;
        TextView ultimaAposta1;
        TextView ultimoSorteio;
        TextView ultimoSorteio1;
    }
}
