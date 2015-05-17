package com.barbon.minhaloteria.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Barbon on 24/04/2015.
 */
public class WebService {

    private static WebService instance;

    public static final String HOST = "http://servicos.albertino.eti.br";
    public static final String GET_LOTOFACIL_POR_CONCURSO = HOST + "/Loteria.asmx/GetLotoFacil_PorConcurso_JSON?numero=";
    public static final String GET_LOTOFACIL_ULTIMO_CONCURSO = HOST + "/Loteria.asmx/GetLotoFacil_UltimoConcurso_JSON?";
    public static final String GET_QUINA_POR_CONCURSO = HOST + "/Loteria.asmx/GetQuina_PorConcurso_JSON?numero=";
    public static final String GET_QUINA_ULTIMO_CONCURSO = HOST + "/Loteria.asmx/GetQuina_UltimoConcurso_JSON?";
    public static final String GET_MEGA_POR_CONCURSO = HOST + "/Loteria.asmx/GetMegaSena_PorConcurso_JSON?numero=";
    public static final String GET_MEGA_ULTIMO_CONCURSO = HOST + "/Loteria.asmx/GetMegaSena_UltimoConcurso_JSON?";
    public static final String GET_SORTEIOS_HOJE = HOST + "/Loteria.asmx/GetSorteiosHoje_JSON?";

    private WebService(){

    }

    public static WebService getInstance(){

        if (instance == null)
            instance = new WebService();

        return instance;
    }

    public String requisicao(String u){
        String l;
        StringBuffer sb = new StringBuffer();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(u);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-length", "0");
            urlConnection.setUseCaches(false);
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((l = in.readLine()) != null){
                Log.i("WEB", l);
                sb.append(l);
            }

            in.close();

            l = sb.toString();
        }
        catch (MalformedURLException e){

            l = "";
            e.printStackTrace();

        }
        catch (IOException e){

            l = "";
            e.printStackTrace();

        }
        finally {

            if (urlConnection != null)
                urlConnection.disconnect();

        }

        return retirarParenteses(l);
    }

    private String retirarParenteses(String s){

        if (s.toUpperCase().contains("\"CONCURSO\"")){
            s = s.trim();
            s = s.substring(1);
            s = s.substring(0, s.length() - 1);

            return s;
        }
        else
            return s;

    }
}
