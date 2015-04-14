package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

/**
 * Created by Barbon on 22/02/2015.
 */
public class Premio {

    public static String[] colunas = new String[]{Premios._ID, Premios.QTDE_ACERTO, Premios.NIVEL, Premios.ID_LOTERIA};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.premio";

    private long id;
    private byte qtdeAcerto;
    private byte nivel;
    private Loteria loteria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getQtdeAcerto() {
        return qtdeAcerto;
    }

    public void setQtdeAcerto(byte qtdeAcerto) {
        this.qtdeAcerto = qtdeAcerto;
    }

    public byte getNivel() {
        return nivel;
    }

    public void setNivel(byte nivel) {
        this.nivel = nivel;
    }

    public Loteria getLoteria() {
        return loteria;
    }

    public void setLoteria(Loteria loteria) {
        this.loteria = loteria;
    }

    public static final class Premios implements BaseColumns{

        public Premios(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/premios");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.premios";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.premios";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String QTDE_ACERTO = DataBaseHelper.COLUNA_QTDE_ACERTO;
        public static final String NIVEL = DataBaseHelper.COLUNA_NIVEL;
        public static final String ID_LOTERIA = DataBaseHelper.COLUNA_ID_LOTERIA;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriPremios = ContentUris.withAppendedId(Premios.CONTENT_URI, id);
            return uriPremios;
        }
    }

    @Override
    public String toString(){
        return "PREMIO ==> Id: " + id + ", Qtde Acerto: " + qtdeAcerto + ", Nivel: " + nivel;
    }
}
