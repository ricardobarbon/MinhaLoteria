package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

import java.util.List;

/**
 * Created by Barbon on 22/02/2015.
 */
public class Sorteio {

    public static String[] colunas = new String[]{Sorteios._ID, Sorteios.NUMERO_SORTEIO, Sorteios.ID_CONCURSO};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.sorteio";

    private long id;
    private int numeroSorteio;
    private List<NumeroSorteado> numerosSorteados;
    private Concurso concurso;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumeroSorteio() {
        return numeroSorteio;
    }

    public void setNumeroSorteio(int numeroSorteio) {
        this.numeroSorteio = numeroSorteio;
    }

    public List<NumeroSorteado> getNumerosSorteados() {
        return numerosSorteados;
    }

    public void setNumerosSorteados(List<NumeroSorteado> numerosSorteados) {
        this.numerosSorteados = numerosSorteados;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public static final class Sorteios implements BaseColumns {

        public Sorteios(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/sorteios");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.sorteios";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.sorteios";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String NUMERO_SORTEIO = DataBaseHelper.COLUNA_NUMERO_SORTEIO;
        public static final String ID_CONCURSO = DataBaseHelper.COLUNA_ID_CONCURSO;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriSorteios = ContentUris.withAppendedId(Sorteios.CONTENT_URI, id);
            return uriSorteios;
        }
    }

    @Override
    public String toString(){
        return "SORTEIO ==> " + id + ", Numero do Sorteio: " + numeroSorteio + ", Concurso: " + concurso;
    }
}
