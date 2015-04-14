package com.barbon.minhaloteria.modelo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

/**
 * Created by Barbon on 22/02/2015.
 */
public class NumeroSorteado extends Numero {

    public static String[] colunas = new String[]{NumerosSorteados._ID, NumerosSorteados.NUMERO, NumerosSorteados.ID_SORTEIO};

    public static final String AUTHORITY = "com.barbon.minhaloteria.modelo.numerosorteado";

    private Sorteio sorteio;

    public Sorteio getSorteio() {
        return sorteio;
    }

    public void setSorteio(Sorteio sorteio) {
        this.sorteio = sorteio;
    }

    /**
     * Classe interna para representar as colunas e ser utilizada por um Content
     * Provider
     *
     * Filha de BaseColumns que já define (_id e _count), para seguir o padrão
     * Android
     */
    public static final class NumerosSorteados implements BaseColumns {

        public NumerosSorteados(){

        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/numerossorteados");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.numerossorteados";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.numerossorteados";

        public static String DEFAULT_SORT_ORDER = DataBaseHelper.COLUNA_ID + " ASC";

        public static final String NUMERO = DataBaseHelper.COLUNA_NUMERO;
        public static final String ID_SORTEIO = DataBaseHelper.COLUNA_ID_SORTEIO;

        // Método que constrói uma Uri para um Carro específico, com o seu id
        // A Uri é no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriNumerosSorteados = ContentUris.withAppendedId(NumerosSorteados.CONTENT_URI, id);
            return uriNumerosSorteados;
        }
    }

    @Override
    public String toString(){
        return "NUMERO SORTEADO ==> Id: " + this.getId() + ", Numero: " + this.getNumero();
    }
}
