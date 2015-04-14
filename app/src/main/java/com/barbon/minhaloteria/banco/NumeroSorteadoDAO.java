package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;

import com.barbon.minhaloteria.modelo.NumeroSorteado;

/**
 * Created by Barbon on 18/03/2015.
 */
public class NumeroSorteadoDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_NUMEROSORTEADO;

    public NumeroSorteadoDAO(Context context){
        super(context);
    }

    public long salvar(NumeroSorteado numeroSorteado){

        long id = numeroSorteado.getId();

        if (id!=0)
            atualizar(numeroSorteado);
        else
            id = inserir(numeroSorteado);

        return id;
    }

    private long inserir(NumeroSorteado numeroSorteado){
        ContentValues values = new ContentValues();

        values.put(NumeroSorteado.NumerosSorteados.NUMERO, numeroSorteado.getNumero());
        values.put(NumeroSorteado.NumerosSorteados.ID_SORTEIO, numeroSorteado.getSorteio().getId());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(NumeroSorteado numeroSorteado){
        ContentValues values = new ContentValues();

        values.put(NumeroSorteado.NumerosSorteados.NUMERO, numeroSorteado.getNumero());
        values.put(NumeroSorteado.NumerosSorteados.ID_SORTEIO, numeroSorteado.getSorteio().getId());

        String _id = String.valueOf(numeroSorteado.getId());
        String where = NumeroSorteado.NumerosSorteados._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = NumeroSorteado.NumerosSorteados._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }
}
