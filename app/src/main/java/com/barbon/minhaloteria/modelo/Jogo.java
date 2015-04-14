package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

import java.util.List;

/**
 * Created by Barbon on 22/02/2015.
 */
public class Jogo {

    public static String[] colunas = new String[]{Jogos._ID, Jogos.DESCRICAO, Jogos.JOGO_PERMANENTE, Jogos.ID_LOTERIA};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.jogo";

    private long id;
    private String descricao;
    private boolean jogoPermanente;
    private List<NumeroJogado> numerosJogados;
    private Loteria loteria;
    private List<JogoConcurso> jogoConcursos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isJogoPermanente() {
        return jogoPermanente;
    }

    public void setJogoPermanente(boolean jogoPermanente) {
        this.jogoPermanente = jogoPermanente;
    }

    public List<NumeroJogado> getNumerosJogados() {
        return numerosJogados;
    }

    public void setNumerosJogados(List<NumeroJogado> numerosJogados) {
        this.numerosJogados = numerosJogados;
    }

    public Loteria getLoteria() {
        return loteria;
    }

    public void setLoteria(Loteria loteria) {
        this.loteria = loteria;
    }

    public List<JogoConcurso> getJogoConcursos() {
        return jogoConcursos;
    }

    public void setJogoConcursos(List<JogoConcurso> jogoConcursos) {
        this.jogoConcursos = jogoConcursos;
    }

    public static final class Jogos implements BaseColumns{

        public Jogos(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/jogos");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.jogos";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.jogos";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String DESCRICAO = DataBaseHelper.COLUNA_DESCRICAO;
        public static final String JOGO_PERMANENTE = DataBaseHelper.COLUNA_JOGO_PERMANENTE;
        public static final String ID_LOTERIA = DataBaseHelper.COLUNA_ID_LOTERIA;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriJogos = ContentUris.withAppendedId(Jogos.CONTENT_URI, id);
            return uriJogos;
        }
    }

    @Override
    public String toString(){
        return "JOGO ==> ID: " + id + ", Descricao: " + descricao + ", Jogo Permanente: " + jogoPermanente + ", Loteria: " + loteria;
    }
}
