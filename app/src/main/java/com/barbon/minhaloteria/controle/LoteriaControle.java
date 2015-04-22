package com.barbon.minhaloteria.controle;

import android.content.Context;
import android.util.Log;

import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.modelo.Loteria;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Barbon on 20/04/2015.
 */
public class LoteriaControle {

    public static final String LOTOFACIL = "LOTOFÁCIL";
    public static final String MEGASENA = "MEGA-SENA";
    public static final String QUINA = "QUINA";

    private static LoteriaControle instance;

    private List<Loteria> loterias;

    private LoteriaControle(Context context){
        LoteriaDAO loteriaDAO = new LoteriaDAO(context);

        loterias = loteriaDAO.listarLoterias();
    }

    public static LoteriaControle getInstance(Context context){

        if (instance == null)
            instance = new LoteriaControle(context);

        return instance;
    }

    public List<Loteria> getLoterias() {
        return loterias;
    }

    public void setLoterias(List<Loteria> loterias) {
        this.loterias = loterias;
    }

    public void criarLoterias(Context context){

        Loteria l;
        DescricaoComparator comp = new DescricaoComparator();

        l = criarLoteria(LOTOFACIL, 18, 15);

        Collections.sort(loterias, comp);

        if (Collections.binarySearch(loterias, l, comp) < 0) {
            salvarLoteria(l, context);
        }

        l = criarLoteria(QUINA, 7, 5);

        if (Collections.binarySearch(loterias, l, comp) < 0) {
            salvarLoteria(l, context);
        }

        l = criarLoteria(MEGASENA, 15, 6);

        if (Collections.binarySearch(loterias, l, comp) < 0) {
            salvarLoteria(l, context);
        }
    }

    private Loteria criarLoteria(String descricao, int maxMarcacao, int minMarcacao){
        PremioControle p = PremioControle.getInstance();
        Loteria l = new Loteria();

        l.setDescricao(descricao);
        l.setQtdeMaxMarcacao((byte) maxMarcacao);
        l.setQtdeMinMarcacao((byte) minMarcacao);

        switch (descricao){
            case LOTOFACIL:
                l.setPremios(p.criaPremiosLotofacil());
                break;
            case QUINA:
                l.setPremios(p.criaPremiosQuina());
                break;
            case MEGASENA:
                l.setPremios(p.criaPremiosMegaSena());
                break;
        }

        return l;
    }

    private void salvarLoteria(Loteria l, Context c){

        LoteriaDAO loteriaDAO = new LoteriaDAO(c);

        loteriaDAO.salvarLoteria(l);

    }

    public class DescricaoComparator implements Comparator<Loteria>{

        @Override
        public int compare(Loteria loteria, Loteria outraLoteria) {
            return loteria.getDescricao().compareTo(outraLoteria.getDescricao());
        }
    }
}
