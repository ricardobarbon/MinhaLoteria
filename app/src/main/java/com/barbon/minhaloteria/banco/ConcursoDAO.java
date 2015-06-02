package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.Loteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Barbon on 08/03/2015.
 */
public class ConcursoDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_CONCURSO;

    public static final String QUERY_ULTIMO_CONCURSO_COM_SORTEIO_POR_LOTERIA =
            "SELECT C." + DataBaseHelper.COLUNA_ID + " " +
            "FROM " + DataBaseHelper.TABELA_LOTERIA + " L " +
            "INNER JOIN " + DataBaseHelper.TABELA_CONCURSO + " C " +
                "ON L." + DataBaseHelper.COLUNA_ID + " = C." + DataBaseHelper.COLUNA_ID_LOTERIA + " " +
            "INNER JOIN " +DataBaseHelper.TABELA_SORTEIO + " S " +
                 "ON C." + DataBaseHelper.COLUNA_ID + " = C." + DataBaseHelper.COLUNA_ID_CONCURSO + " " +
            "WHERE L." + DataBaseHelper.COLUNA_ID + " = ? " +
            "ORDER BY C." + DataBaseHelper.COLUNA_NUMERO + " DESC LIMIT 1";

    public static final String QUERY_ULTIMO_CONCURSO_POR_LOTERIA =
            "SELECT " + DataBaseHelper.COLUNA_ID + " " +
            "FROM " + DataBaseHelper.TABELA_CONCURSO + " " +
            "WHERE " + DataBaseHelper.COLUNA_ID_LOTERIA + " = ? " +
            "ORDER BY " + DataBaseHelper.COLUNA_NUMERO + " DESC LIMIT 1";

    public static final String QUERY_ULTIMO_CONCURSO_POR_JOGO =
                    "SELECT C." + DataBaseHelper.COLUNA_ID + " " +
                    "FROM " + DataBaseHelper.TABELA_CONCURSO + " C " +
                    "INNER JOIN " + DataBaseHelper.TABELA_JOGOCONSURSO + " JC " +
                            "ON C." + DataBaseHelper.COLUNA_ID + " = JC." + DataBaseHelper.COLUNA_ID_CONCURSO + " " +
                    "WHERE JC." + DataBaseHelper.COLUNA_ID_JOGO + " = ? " +
                    "ORDER BY C." + DataBaseHelper.COLUNA_NUMERO + " DESC LIMIT 1";

    public static final String QUERY_ULTIMO_CONCURSO_COM_SORTEIO_POR_JOGO =
            "SELECT C." + DataBaseHelper.COLUNA_ID + " " +
                    "FROM " + DataBaseHelper.TABELA_JOGO + " J " +
                    "INNER JOIN " + DataBaseHelper.TABELA_CONCURSO + " C " +
                    "ON J." + DataBaseHelper.COLUNA_ID + " = C." + DataBaseHelper.COLUNA_ID_JOGO + " " +
                    "INNER JOIN " +DataBaseHelper.TABELA_SORTEIO + " S " +
                    "ON C." + DataBaseHelper.COLUNA_ID + " = C." + DataBaseHelper.COLUNA_ID_CONCURSO + " " +
                    "WHERE J." + DataBaseHelper.COLUNA_ID + " = ? " +
                    "ORDER BY C." + DataBaseHelper.COLUNA_NUMERO + " DESC LIMIT 1";

    public ConcursoDAO(Context context){
        super(context);
    }

    public long salvar(Concurso concurso){

        long id = concurso.getId();

        if (id != 0)
            atualizar(concurso);
        else
            id = inserir(concurso);

        return id;
    }

    private long inserir(Concurso concurso){
        ContentValues values = new ContentValues();

        values.put(Concurso.Concursos.NUMERO, concurso.getNumero());
        values.put(Concurso.Concursos.DATA, concurso.getData().getTime());
        values.put(Concurso.Concursos.ID_LOTERIA, concurso.getLoteria().getId());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(Concurso concurso){
        ContentValues values = new ContentValues();

        values.put(Concurso.Concursos.NUMERO, concurso.getNumero());
        values.put(Concurso.Concursos.DATA, concurso.getData().getTime());
        values.put(Concurso.Concursos.ID_LOTERIA, concurso.getLoteria().getId());

        String _id = String.valueOf(concurso.getId());
        String where = Concurso.Concursos._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = Concurso.Concursos._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public Concurso buscarConcurso(long id){

        Cursor c = database.query(true, NOME_TABELA, Concurso.colunas, Concurso.Concursos._ID + "=" + id, null, null, null, null, null);

        if (c.getCount() > 0){
            c.moveToFirst();
            Concurso concurso = new Concurso();

            concurso.setId(c.getLong(0));
            concurso.setNumero(c.getInt(1));
            concurso.setData(new Date(c.getLong(2)));

            return concurso;
        }

        return null;
    }

    public List<Concurso> listarConcursos(){

        Cursor c = database.query(NOME_TABELA, Concurso.colunas, null, null, null, null, null, null);

        List<Concurso> concursos = new ArrayList<Concurso>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(Concurso.Concursos._ID);
            int idxNumero = c.getColumnIndex(Concurso.Concursos.NUMERO);
            int idxData = c.getColumnIndex(Concurso.Concursos.DATA);

            do{
                Concurso con = new Concurso();
                concursos.add(con);

                con.setId(c.getLong(idxId));
                con.setNumero(c.getInt(idxNumero));
                con.setData(new Date(c.getLong(idxData)));

            }while (c.moveToNext());
        }

        return concursos;
    }

    public Concurso ultimoConcursoComSorteio(Loteria loteria){

        String[] w = {loteria.getId() + ""};

        Cursor c = database.rawQuery(QUERY_ULTIMO_CONCURSO_COM_SORTEIO_POR_LOTERIA, w);

        if (c.moveToFirst()){
            return buscarConcurso(c.getLong(0));
        }

        return null;
    }

    public Concurso ultimoConcurso(Loteria loteria){

        String[] w = {loteria.getId() + ""};

        Cursor c = database.rawQuery(QUERY_ULTIMO_CONCURSO_POR_LOTERIA, w);

        if (c.moveToFirst()){
            return buscarConcurso(c.getLong(0));
        }

        return null;
    }

    public Concurso ultimoConcurso(Jogo jogo){

        String[] w = {jogo.getId() + ""};

        Cursor c = database.rawQuery(QUERY_ULTIMO_CONCURSO_POR_JOGO, w);

        if (c.moveToFirst()){
            return buscarConcurso(c.getLong(0));
        }

        return null;
    }

    public Concurso ultimoConcursoComSorteio(Jogo jogo){

        String[] w = {jogo.getId() + ""};

        Cursor c = database.rawQuery(QUERY_ULTIMO_CONCURSO_COM_SORTEIO_POR_JOGO, w);

        if (c.moveToFirst()){
            return buscarConcurso(c.getLong(0));
        }

        return null;
    }
}
