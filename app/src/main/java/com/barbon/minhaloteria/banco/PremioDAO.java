package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.Premio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barbon on 16/03/2015.
 */
public class PremioDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_PREMIO;

    public PremioDAO(Context context){
        super(context);
    }

    public long salvar(Premio premio){

        long id = premio.getId();

        if (id!=0)
            atualizar(premio);
        else
            id = inserir(premio);

        return id;
    }

    private long inserir(Premio premio){
        ContentValues values = new ContentValues();

        values.put(Premio.Premios.QTDE_ACERTO, premio.getQtdeAcerto());
        values.put(Premio.Premios.NIVEL, premio.getNivel());
        values.put(Premio.Premios.ID_LOTERIA, premio.getLoteria().getId());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(Premio premio){
        ContentValues values = new ContentValues();

        values.put(Premio.Premios.QTDE_ACERTO, premio.getQtdeAcerto());
        values.put(Premio.Premios.NIVEL, premio.getNivel());
        values.put(Premio.Premios.ID_LOTERIA, premio.getLoteria().getId());

        String _id = String.valueOf(premio.getId());
        String where = Premio.Premios._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = Premio.Premios._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public Premio buscarPremio(long id){

        Cursor c = database.query(true, NOME_TABELA, Premio.colunas, Premio.Premios._ID + "=" + id, null, null, null, null, null);

        if (c.getCount() > 0){
            c.moveToFirst();
            Premio premio = new Premio();

            premio.setId(c.getLong(0));
            premio.setQtdeAcerto((byte) c.getInt(1));
            premio.setNivel((byte) c.getInt(2));

            return premio;
        }

        return null;
    }

    public List<Premio> listarPremios(){

        Cursor c = database.query(NOME_TABELA, Premio.colunas, null, null, null, null, null, null);

        List<Premio> premios = new ArrayList<Premio>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(Premio.Premios._ID);
            int idxQtdeAcerto = c.getColumnIndex(Premio.Premios.QTDE_ACERTO);
            int idxNivel = c.getColumnIndex(Premio.Premios.NIVEL);

            do{
                Premio p = new Premio();
                premios.add(p);

                p.setId(c.getLong(idxId));
                p.setQtdeAcerto((byte)c.getInt(idxQtdeAcerto));
                p.setNivel((byte)c.getInt(idxNivel));

            }while (c.moveToNext());
        }

        return premios;
    }

    public List<Premio> listarPremiosPorLoteria(Loteria loteria){

        Cursor c = database.query(NOME_TABELA, Premio.colunas, Premio.Premios.ID_LOTERIA + " = " + loteria.getId(), null, null, null, null, null);

        List<Premio> premios = new ArrayList<Premio>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(Premio.Premios._ID);
            int idxQtdeAcerto = c.getColumnIndex(Premio.Premios.QTDE_ACERTO);
            int idxNivel = c.getColumnIndex(Premio.Premios.NIVEL);

            do{
                Premio p = new Premio();
                premios.add(p);

                p.setId(c.getLong(idxId));
                p.setQtdeAcerto((byte)c.getInt(idxQtdeAcerto));
                p.setNivel((byte)c.getInt(idxNivel));
                p.setLoteria(loteria);

            }while (c.moveToNext());
        }

        return premios;
    }

}
