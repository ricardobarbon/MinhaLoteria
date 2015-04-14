package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by Barbon on 22/02/2015.
 */
public class Concurso {

    public static String[] colunas = new String[]{Concursos._ID, Concursos.NUMERO, Concursos.DATA, Concursos.ID_LOTERIA};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.concurso";

    private long id;
    private int numero;
    private Date data;
    private List<Sorteio> sorteios;
    private Loteria loteria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Sorteio> getSorteios() {
        return sorteios;
    }

    public void setSorteios(List<Sorteio> sorteios) {
        this.sorteios = sorteios;
    }

    public Loteria getLoteria() {
        return loteria;
    }

    public void setLoteria(Loteria loteria) {
        this.loteria = loteria;
    }

    /**
     * Classe interna para representar as colunas e ser utilizada por um Content
     * Provider
     *
     * Filha de BaseColumns que já define (_id e _count), para seguir o padrão
     * Android
     */
    public static final class Concursos implements BaseColumns{

        public Concursos(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/concursos");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.concursos";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.concursos";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String NUMERO = DataBaseHelper.COLUNA_NUMERO;
        public static final String DATA = DataBaseHelper.COLUNA_DATA;
        public static final String ID_LOTERIA = DataBaseHelper.COLUNA_ID_LOTERIA;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriConsurso = ContentUris.withAppendedId(Concursos.CONTENT_URI, id);
            return uriConsurso;
        }
    }

    @Override
    public String toString() {
        return "CONCURSO ==> ID:" + id +", Numero: " + numero + ", Data: " + data + ", Id_loteria: " + loteria;
    }
}
