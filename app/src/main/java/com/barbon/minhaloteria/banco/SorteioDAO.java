package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.barbon.minhaloteria.modelo.Sorteio;

/**
 * Created by Barbon on 18/03/2015.
 */
public class SorteioDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_SORTEIO;

    public SorteioDAO(Context context){
        super(context);
    }

    public long salvar(Sorteio sorteio){

        long id = sorteio.getId();

        if (id!=0)
            atualizar(sorteio);
        else
            id = inserir(sorteio);

        return id;
    }

    private long inserir(Sorteio sorteio){
        ContentValues values = new ContentValues();

        values.put(Sorteio.Sorteios.NUMERO_SORTEIO, sorteio.getNumeroSorteio());
        values.put(Sorteio.Sorteios.ID_CONCURSO, sorteio.getConcurso().getId());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(Sorteio sorteio){
        ContentValues values = new ContentValues();

        values.put(Sorteio.Sorteios.NUMERO_SORTEIO, sorteio.getNumeroSorteio());
        values.put(Sorteio.Sorteios.ID_CONCURSO, sorteio.getConcurso().getId());

        String _id = String.valueOf(sorteio.getId());
        String where = Sorteio.Sorteios._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = Sorteio.Sorteios._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public Sorteio buscarSorteio(long id){

        Cursor c = database.query(true, NOME_TABELA, Sorteio.colunas, Sorteio.Sorteios._ID + "=" + id, null, null, null, null, null);

        if (c.getCount() > 0){
            NumeroSorteadoDAO numeroSorteadoDAO = new NumeroSorteadoDAO(this.getmContext());

            c.moveToFirst();
            Sorteio sorteio = new Sorteio();

            sorteio.setId(c.getLong(0));
            sorteio.setNumeroSorteio(c.getInt(1));
            sorteio.setNumerosSorteados(numeroSorteadoDAO.buscarNumerosSorteados(sorteio));

            return sorteio;
        }

        return null;
    }
}
