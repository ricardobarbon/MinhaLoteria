package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.NumeroJogado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barbon on 18/03/2015.
 */
public class NumeroJogadoDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_NUMEROJOGADO;

    public NumeroJogadoDAO(Context context){
        super(context);
    }

    public long salvar(NumeroJogado numeroJogado){

        long id = numeroJogado.getId();

        if (id!=0)
            atualizar(numeroJogado);
        else
            id = inserir(numeroJogado);

        return id;
    }

    private long inserir(NumeroJogado numeroJogado){
        ContentValues values = new ContentValues();

        values.put(NumeroJogado.NumerosJogados.NUMERO, numeroJogado.getNumero());
        values.put(NumeroJogado.NumerosJogados.ID_JOGO, numeroJogado.getJogo().getId());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(NumeroJogado numeroJogado){
        ContentValues values = new ContentValues();

        values.put(NumeroJogado.NumerosJogados.NUMERO, numeroJogado.getNumero());
        values.put(NumeroJogado.NumerosJogados.ID_JOGO, numeroJogado.getJogo().getId());

        String _id = String.valueOf(numeroJogado.getId());
        String where = NumeroJogado.NumerosJogados._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = NumeroJogado.NumerosJogados._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public List<NumeroJogado> buscarNumeros(Jogo jogo){

        Cursor c = database.query(NOME_TABELA, NumeroJogado.colunas, NumeroJogado.NumerosJogados.ID_JOGO + " = " + jogo.getId(),
                                  null, null, null, NumeroJogado.NumerosJogados.NUMERO, null);

        List<NumeroJogado> numeroJogados = new ArrayList<NumeroJogado>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(NumeroJogado.NumerosJogados._ID);
            int idxNumero = c.getColumnIndex(NumeroJogado.NumerosJogados.NUMERO);

            do{
                NumeroJogado n = new NumeroJogado();
                numeroJogados.add(n);

                n.setId(c.getLong(idxId));
                n.setNumero((byte)c.getInt(idxNumero));
                n.setJogo(jogo);

            }while (c.moveToNext());
        }

        return numeroJogados;
    }

}
