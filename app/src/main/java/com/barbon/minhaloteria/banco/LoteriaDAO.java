package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.barbon.minhaloteria.modelo.Loteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barbon on 16/03/2015.
 */
public class LoteriaDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_LOTERIA;

    public LoteriaDAO(Context context){
        super(context);
    }

    public long salvar(Loteria loteria){

        long id = loteria.getId();

        if (id != 0)
            atualizar(loteria);
        else
            id = inserir(loteria);

        return id;
    }

    private long inserir(Loteria loteria){
        ContentValues values = new ContentValues();

        values.put(Loteria.Loterias.DESCRICAO, loteria.getDescricao());
        values.put(Loteria.Loterias.QTDE_MAX_MARCACAO, loteria.getQtdeMaxMarcacao());
        values.put(Loteria.Loterias.QTDE_MIN_MARCACAO, loteria.getQtdeMinMarcacao());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(Loteria loteria){
        ContentValues values = new ContentValues();

        values.put(Loteria.Loterias.DESCRICAO, loteria.getDescricao());
        values.put(Loteria.Loterias.QTDE_MAX_MARCACAO, loteria.getQtdeMaxMarcacao());
        values.put(Loteria.Loterias.QTDE_MIN_MARCACAO, loteria.getQtdeMinMarcacao());

        String _id = String.valueOf(loteria.getId());
        String where = Loteria.Loterias._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = Loteria.Loterias._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public Loteria buscarLoteria(long id){

        Cursor c = database.query(true, NOME_TABELA, Loteria.colunas, Loteria.Loterias._ID + "=" + id, null, null, null, null, null);

        if (c.getCount() > 0){
            c.moveToFirst();
            Loteria loteria = new Loteria();

            loteria.setId(c.getLong(0));
            loteria.setDescricao(c.getString(1));
            loteria.setQtdeMinMarcacao((byte)c.getInt(2));
            loteria.setQtdeMaxMarcacao((byte)c.getInt(3));

            return loteria;
        }

        return null;
    }

    public List<Loteria> listarLoterias(){

        Cursor c = database.query(NOME_TABELA, Loteria.colunas, null, null, null, null, null, null);

        List<Loteria> loterias = new ArrayList<Loteria>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(Loteria.Loterias._ID);
            int idxDescricao = c.getColumnIndex(Loteria.Loterias.DESCRICAO);
            int idxQtdeMin = c.getColumnIndex(Loteria.Loterias.QTDE_MIN_MARCACAO);
            int idxQtdeMax = c.getColumnIndex(Loteria.Loterias.QTDE_MAX_MARCACAO);

            do{
                Loteria l = new Loteria();
                loterias.add(l);

                l.setId(c.getLong(idxId));
                l.setDescricao(c.getString(idxDescricao));
                l.setQtdeMinMarcacao((byte)c.getInt(idxQtdeMin));
                l.setQtdeMaxMarcacao((byte)c.getInt(idxQtdeMax));

            }while (c.moveToNext());
        }

        return loterias;
    }
}
