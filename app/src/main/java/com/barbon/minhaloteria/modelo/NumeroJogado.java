package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

/**
 * Created by Barbon on 22/02/2015.
 */
public class NumeroJogado extends Numero {

    public static String[] colunas = new String[]{NumerosJogados._ID, NumerosJogados.NUMERO, NumerosJogados.ID_JOGO};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.numerojogado";

    private Jogo jogo;

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    /**
     * Classe interna para representar as colunas e ser utilizada por um Content
     * Provider
     *
     * Filha de BaseColumns que já define (_id e _count), para seguir o padrão
     * Android
     */
    public static final class NumerosJogados implements BaseColumns {

        public NumerosJogados(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/numerosjogados");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.numerosjogados";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.numerosjogados";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String NUMERO = DataBaseHelper.COLUNA_NUMERO;
        public static final String ID_JOGO = DataBaseHelper.COLUNA_ID_JOGO;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriNumerosJogados = ContentUris.withAppendedId(NumerosJogados.CONTENT_URI, id);
            return uriNumerosJogados;
        }
    }

    @Override
    public String toString(){
        return "NUMERO JOGADO ==> Id: " + this.getId() + ", Numero: " + this.getNumero();
    }
}
