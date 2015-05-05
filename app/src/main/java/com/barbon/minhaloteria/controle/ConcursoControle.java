package com.barbon.minhaloteria.controle;

import android.content.Context;

import com.barbon.minhaloteria.banco.ConcursoDAO;
import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.util.WebService;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Barbon on 01/05/2015.
 */
public class ConcursoControle {

    private static ConcursoControle instance;

    private ConcursoControle(){

    }

    public static ConcursoControle getInstance(){

        if (instance == null)
            instance = new ConcursoControle();

        return instance;
    }

    public void sincroniza(Context context){

        LoteriaDAO loteriaDAO = new LoteriaDAO(context);

        List<Loteria> loterias = loteriaDAO.listarLoterias();

        for (Loteria l: loterias){

        }
    }

    /**************************************************
     * WEB SERVICE
     **************************************************/

    public String getSorteiosHoje(){

        WebService w = WebService.getInstance();

        return w.requisicao(WebService.GET_SORTEIOS_HOJE);
    }

    public String getUltimoConcurso(Loteria loteria){

        WebService w = WebService.getInstance();
        String url = "";

        switch (loteria.getDescricao()){
            case LoteriaControle.MEGASENA:
                url = WebService.GET_MEGA_ULTIMO_CONCURSO;
                break;
            case LoteriaControle.QUINA:
                url = WebService.GET_QUINA_ULTIMO_CONCURSO;
                break;
            case LoteriaControle.LOTOFACIL:
                url = WebService.GET_LOTOFACIL_ULTIMO_CONCURSO;
                break;
            default:
                throw new IllegalArgumentException("Loteria não encontrada");
        }

        return w.requisicao(url);
    }

    public String getPorConcurso(Concurso concurso){

        WebService w = WebService.getInstance();
        String url = "";

        switch (concurso.getLoteria().getDescricao()){
            case LoteriaControle.MEGASENA:
                url = WebService.GET_MEGA_POR_CONCURSO;
                break;
            case LoteriaControle.QUINA:
                url = WebService.GET_QUINA_POR_CONCURSO;
                break;
            case LoteriaControle.LOTOFACIL:
                url = WebService.GET_LOTOFACIL_POR_CONCURSO;
                break;
            default:
                throw new IllegalArgumentException("Loteria não encontrada");
        }

        return w.requisicao(url + concurso.getNumero());
    }

    private class ConcursoWebService{
        @SerializedName("Concurso")
        int concurso;
        @SerializedName("Data")
        Date data;
        @SerializedName("Dezenas")
        List<Integer> dezenas;
    }
}
