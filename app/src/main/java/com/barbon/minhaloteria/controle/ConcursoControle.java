package com.barbon.minhaloteria.controle;

import android.util.Log;

import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.NumeroSorteado;
import com.barbon.minhaloteria.modelo.Sorteio;
import com.barbon.minhaloteria.util.ConcursoWebService;
import com.barbon.minhaloteria.util.WebService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Barbon on 01/05/2015.
 */
public class ConcursoControle {

    private static ConcursoControle instance;
    private static Gson gson;

    private ConcursoControle(){

    }

    public static ConcursoControle getInstance(){

        if (instance == null)
            instance = new ConcursoControle();

        return instance;
    }

    public void inicializeGson(){

        if (gson == null){
            gson = new Gson();
        }
    }

    public Concurso getUltimoConcursoW(Loteria loteria){

        String resp = "";

        inicializeGson();

        resp = getUltimoConcursoWebService(loteria);

        if (isJSONValid(resp))
            return concursoWebServiceParaConcurso(resp);
        else
            return null;

    }

    public Concurso getPorConcursoW(Concurso concurso){

        String resp = "";

        inicializeGson();

        resp = getPorConcursoWebService(concurso);

        if (isJSONValid(resp))
            return concursoWebServiceParaConcurso(resp);
        else
            return null;

    }

    /**************************************************
     * WEB SERVICE
     **************************************************/

    private String getSorteiosHojeWebService(){

        WebService w = WebService.getInstance();

        return w.requisicao(WebService.GET_SORTEIOS_HOJE);
    }

    private String getUltimoConcursoWebService(Loteria loteria){

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

    private String getPorConcursoWebService(Concurso concurso){

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

    private static boolean isJSONValid(String JSON_STRING) {

        //return JSON_STRING.toUpperCase().contains("\"CONCURSO\"");

        try {
            gson.fromJson(JSON_STRING, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    private Concurso concursoWebServiceParaConcurso(String json){

        ConcursoWebService c = new ConcursoWebService();
        Concurso concurso = new Concurso();
        List<Sorteio> sorteios = new ArrayList<>();
        Sorteio sorteio = new Sorteio();

        sorteios.add(sorteio);

        c = gson.fromJson(json, ConcursoWebService.class);

        concurso.setNumero(c.concurso);
        concurso.setData(stringParaDate(c.data));
        concurso.setSorteios(sorteios);

        sorteio.setNumeroSorteio(1);
        sorteio.setConcurso(concurso);
        sorteio.setNumerosSorteados(recuperaNumerosSorteados(sorteio, c.dezenas));

        return concurso;
    }

    private Date stringParaDate(String data){

        return new Date(Long.parseLong(data.replaceAll("[^0-9]", "")));

    }

    private List<NumeroSorteado> recuperaNumerosSorteados(Sorteio sorteio, String numeros){

        List<NumeroSorteado> numeroSorteados = new ArrayList<>();
        String[] numeros2 = numeros.split("\\|");

        for (String n: numeros2 ){
            NumeroSorteado ns = new NumeroSorteado();

            ns.setSorteio(sorteio);
            ns.setNumero(Byte.parseByte(n));
            numeroSorteados.add(ns);
        }

        return numeroSorteados;
    }
}
