package com.barbon.minhaloteria.controle;

import android.content.Context;
import android.util.Log;

import com.barbon.minhaloteria.R;
import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.banco.PremioDAO;
import com.barbon.minhaloteria.modelo.Jogo;
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

    public static final int LOTOFACIL_IMAGEM = R.drawable.logo_lotofacil_gde;
    public static final int MEGASENA_IMAGEM = R.drawable.logo_megasena_gde;
    public static final int QUINA_IMAGEM = R.drawable.logo_quina_gde;

    private static final int LOTOFACIL_BACKGROUND = R.drawable.back_lotofacil;
    private static final int MEGASENA_BACKGROUND = R.drawable.back_mega;
    private static final int QUINA_BACKGROUND = R.drawable.back_quina;

    private static final int LOTOFACIL_BACKGROUND_MARCADO = R.drawable.back_lotofacil_marcado;
    private static final int MEGASENA_BACKGROUND_MARCADO = R.drawable.back_mega_marcado;
    private static final int QUINA_BACKGROUND_MARCADO = R.drawable.back_quina_marcado;

    private static final int LOTOFACIL_COR = R.color.lotofacil;
    private static final int MEGASENA_COR= R.color.mega;
    private static final int QUINA_COR = R.color.quina;

    public static  final int LOTOFACIL_PROXIMOS_CONCURSOS = 12;
    public static  final int MEGASENA_PROXIMOS_CONCURSOS = 8;
    public static  final int QUINA_PROXIMOS_CONCURSOS = 24;

    private static final int LOTOFACIL_QTDE_NUMEROS = 25;
    private static final int MEGA_QTDE_NUMEROS = 60;
    private static final int QUINA_QTDE_NUMEROS = 80;

    private static LoteriaControle instance;
    private static List<Loteria> loterias;

    private LoteriaControle(){

    }

    public static LoteriaControle getInstance(){

        if (instance == null)
            instance = new LoteriaControle();

        return instance;
    }

    public static List<Loteria> getLoterias(Context context){
        if (loterias == null)
            loterias = getInstance().todasLoterias(context);

        return loterias;
    }

    public void criarLoterias(Context context){

        Loteria l;
        DescricaoComparator comp = new DescricaoComparator();
        LoteriaDAO loteriaDAO = new LoteriaDAO(context);

        List<Loteria> loterias = loteriaDAO.listarLoterias();

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
                l.setPremios(p.criaPremiosLotofacil(l));
                break;
            case QUINA:
                l.setPremios(p.criaPremiosQuina(l));
                break;
            case MEGASENA:
                l.setPremios(p.criaPremiosMegaSena(l));
                break;
        }

        return l;
    }

    private void salvarLoteria(Loteria l, Context c){

        LoteriaDAO loteriaDAO = new LoteriaDAO(c);

        loteriaDAO.salvarLoteria(l);

    }

    public int quantProximosConcursos(Loteria loteria){

        switch (loteria.getDescricao()){
            case LOTOFACIL:
                return LOTOFACIL_PROXIMOS_CONCURSOS;
            case QUINA:
                return QUINA_PROXIMOS_CONCURSOS;
            case MEGASENA:
                return MEGASENA_PROXIMOS_CONCURSOS;
            default:
                throw new IllegalArgumentException("Loteria inválida...");
        }
    }

    private List<Loteria> todasLoterias(Context context){

        LoteriaDAO loteriaDAO = new LoteriaDAO(context);
        PremioDAO premioDAO = new PremioDAO(context);

        List<Loteria> loterias;

        loterias = loteriaDAO.listarLoterias();

        for (Loteria l: loterias){
            l.setPremios(premioDAO.listarPremiosPorLoteria(l));

            if (l.getDescricao().equalsIgnoreCase(LOTOFACIL)) {
                l.setImagem(LOTOFACIL_IMAGEM);
            }
            else if (l.getDescricao().equalsIgnoreCase(MEGASENA)) {
                l.setImagem(MEGASENA_IMAGEM);
            }
            else if (l.getDescricao().equalsIgnoreCase(QUINA)) {
                l.setImagem(QUINA_IMAGEM);
            }
        }

        return loterias;
    }

    public void definirLoteria(List<Loteria> loterias, Jogo jogo){
        int index;

        IdComparator idComparator = new IdComparator();

        Collections.sort(loterias, idComparator);

        index = Collections.binarySearch(loterias, jogo.getLoteria(), idComparator);

        if (index >= 0)
            jogo.setLoteria(loterias.get(index));
        else
            jogo.setLoteria(null);
    }

    public int recuperarQtdeNumeros(Loteria loteria){
        switch (loteria.getDescricao()){
            case LOTOFACIL:
                return LOTOFACIL_QTDE_NUMEROS;
            case QUINA:
                return QUINA_QTDE_NUMEROS;
            case MEGASENA:
                return MEGA_QTDE_NUMEROS;
            default:
                throw new IllegalArgumentException("Loteria inválida...");
        }
    }

    public int recuperarBackground(Loteria loteria){
        switch (loteria.getDescricao()){
            case LOTOFACIL:
                return LOTOFACIL_BACKGROUND;
            case QUINA:
                return QUINA_BACKGROUND;
            case MEGASENA:
                return MEGASENA_BACKGROUND;
            default:
                throw new IllegalArgumentException("Loteria inválida...");
        }
    }

    public int recuperarBackgroundMarcado(Loteria loteria){
        switch (loteria.getDescricao()){
            case LOTOFACIL:
                return LOTOFACIL_BACKGROUND_MARCADO;
            case QUINA:
                return QUINA_BACKGROUND_MARCADO;
            case MEGASENA:
                return MEGASENA_BACKGROUND_MARCADO;
            default:
                throw new IllegalArgumentException("Loteria inválida...");
        }
    }

    public int recuperarCor(Loteria loteria){
        switch (loteria.getDescricao()){
            case LOTOFACIL:
                return LOTOFACIL_COR;
            case QUINA:
                return QUINA_COR;
            case MEGASENA:
                return MEGASENA_COR;
            default:
                throw new IllegalArgumentException("Loteria inválida...");
        }
    }
    
    public boolean podeContinuarMarcando(Loteria loteria, int qtdeMarcada){

        return qtdeMarcada < loteria.getQtdeMaxMarcacao();

    }

    public static class DescricaoComparator implements Comparator<Loteria>{

        @Override
        public int compare(Loteria loteria, Loteria outraLoteria) {
            return loteria.getDescricao().compareTo(outraLoteria.getDescricao());
        }
    }

    public static class IdComparator implements Comparator<Loteria>{

        @Override
        public int compare(Loteria loteria, Loteria outraLoteria) {

            if (loteria.getId() > outraLoteria.getId())
                return 1;
            else if (loteria.getId() < outraLoteria.getId())
                return -1;
            else
                return 0;
        }
    }
}
