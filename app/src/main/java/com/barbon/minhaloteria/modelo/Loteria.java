package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

import java.util.List;

/**
 * Created by Barbon on 22/02/2015.
 */
public class Loteria {

    public static String[] colunas = new String[]{Loterias._ID, Loterias.DESCRICAO, Loterias.QTDE_MIN_MARCACAO, Loterias.QTDE_MAX_MARCACAO};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.loteria";

    private long id;
    private String descricao;
    private byte qtdeMinMarcacao;
    private byte qtdeMaxMarcacao;
    private List<Premio> premios;

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

    public byte getQtdeMinMarcacao() {
        return qtdeMinMarcacao;
    }

    public void setQtdeMinMarcacao(byte qtdeMinMarcacao) {
        this.qtdeMinMarcacao = qtdeMinMarcacao;
    }

    public byte getQtdeMaxMarcacao() {
        return qtdeMaxMarcacao;
    }

    public void setQtdeMaxMarcacao(byte qtdeMaxMarcacao) {
        this.qtdeMaxMarcacao = qtdeMaxMarcacao;
    }

    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> premios) {
        this.premios = premios;
    }

    /**
     * Classe interna para representar as colunas e ser utilizada por um Content
     * Provider
     *
     * Filha de BaseColumns que já define (_id e _count), para seguir o padrão
     * Android
     */
    public static final class Loterias implements BaseColumns {

        public Loterias(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/loterias");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.loterias";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.loterias";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String DESCRICAO = DataBaseHelper.COLUNA_DESCRICAO;
        public static final String QTDE_MIN_MARCACAO = DataBaseHelper.COLUNA_QTDE_MIN_MARCACAO;
        public static final String QTDE_MAX_MARCACAO = DataBaseHelper.COLUNA_QTDE_MAX_MARCACAO;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriLoteria = ContentUris.withAppendedId(Loterias.CONTENT_URI, id);
            return uriLoteria;
        }
    }

    @Override
    public String toString(){
        return "LOTERIA ==> Id: " + id + ", Descricao: " + descricao + ", Min Marcacao: " + qtdeMinMarcacao + ", Max Marcacao: " + qtdeMaxMarcacao;
    }
}
