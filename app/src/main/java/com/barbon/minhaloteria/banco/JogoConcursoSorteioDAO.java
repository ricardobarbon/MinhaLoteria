package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import com.barbon.minhaloteria.modelo.JogoConcurso;
import com.barbon.minhaloteria.modelo.JogoConcursoSorteio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barbon on 18/03/2015.
 */
public class JogoConcursoSorteioDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_JOGOCONCURSOSORTEIO;

    public JogoConcursoSorteioDAO(Context context){
        super(context);
    }

    public long salvar(JogoConcursoSorteio jogoConcursoSorteio){

        long i = 0;

        try{
            i = inserir(jogoConcursoSorteio);
        }
        catch (SQLiteConstraintException e){
            i = atualizar(jogoConcursoSorteio);
        }
        catch (SQLException e){
            i = -1;
        }

        return i;
    }

    public long inserir(JogoConcursoSorteio jogoConcursoSorteio) throws SQLException{
        ContentValues values = new ContentValues();

        values.put(JogoConcursoSorteio.JogosConcursosSorteios.ID_JOGOCONCURSO, jogoConcursoSorteio.getJogoConcurso().getId());
        values.put(JogoConcursoSorteio.JogosConcursosSorteios.ID_SORTEIO, jogoConcursoSorteio.getSorteio().getId());
        values.put(JogoConcursoSorteio.JogosConcursosSorteios.ID_PREMIO, jogoConcursoSorteio.getPremio().getId());
        values.put(JogoConcursoSorteio.JogosConcursosSorteios.QTDE_ACERTO, jogoConcursoSorteio.getQtdeAcerto());

        return inserirOrThrow(NOME_TABELA, values);
    }

    public long inserirOrThrow(String tabela, ContentValues values) throws SQLException {

        return database.insertOrThrow(tabela, "", values);

    }

    public int atualizar(JogoConcursoSorteio jogoConcursoSorteio){
        ContentValues values = new ContentValues();

        values.put(JogoConcursoSorteio.JogosConcursosSorteios.ID_PREMIO, jogoConcursoSorteio.getPremio().getId());
        values.put(JogoConcursoSorteio.JogosConcursosSorteios.QTDE_ACERTO, jogoConcursoSorteio.getQtdeAcerto());

        String idJogoConcurso = String.valueOf(jogoConcursoSorteio.getJogoConcurso().getId());
        String idSorteio = String.valueOf(jogoConcursoSorteio.getSorteio().getId());
        String where = JogoConcursoSorteio.JogosConcursosSorteios.ID_JOGOCONCURSO + "=? and " +
                       JogoConcursoSorteio.JogosConcursosSorteios.ID_SORTEIO + "=?";
        String[] whereArgs = new String[]{idJogoConcurso, idSorteio};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long idJogoConcurso, long idSorteio){

        String _idJogoConcurso = String.valueOf(idJogoConcurso);
        String _idSorteio = String.valueOf(idSorteio);
        String where = JogoConcursoSorteio.JogosConcursosSorteios.ID_JOGOCONCURSO + "=? and " +
                JogoConcursoSorteio.JogosConcursosSorteios.ID_SORTEIO + "=?";
        String[] whereArgs = new String[]{_idJogoConcurso, _idSorteio};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public List<JogoConcursoSorteio> buscaJogoConcursoSorteio(JogoConcurso jogoConcurso){

        Cursor c = database.query(NOME_TABELA, JogoConcursoSorteio.colunas, JogoConcursoSorteio.JogosConcursosSorteios.ID_JOGOCONCURSO + " = " + jogoConcurso.getId(),
                                  null, null, null, null, null);

        List<JogoConcursoSorteio> jogoConcursoSorteios = new ArrayList<JogoConcursoSorteio>();

        if (c.moveToFirst()){

            PremioDAO premioDAO = new PremioDAO(this.getmContext());
            SorteioDAO sorteioDAO = new SorteioDAO(this.getmContext());

            int idxPremio = c.getColumnIndex(JogoConcursoSorteio.JogosConcursosSorteios.ID_PREMIO);
            int idxSorteio = c.getColumnIndex(JogoConcursoSorteio.JogosConcursosSorteios.ID_SORTEIO);
            int idxQtdeAcerto = c.getColumnIndex(JogoConcursoSorteio.JogosConcursosSorteios.QTDE_ACERTO);

            do{
                JogoConcursoSorteio jcs = new JogoConcursoSorteio();
                jogoConcursoSorteios.add(jcs);

                jcs.setJogoConcurso(jogoConcurso);
                jcs.setPremio(premioDAO.buscarPremio(c.getLong(idxPremio)));
                jcs.setSorteio(sorteioDAO.buscarSorteio(c.getLong(idxSorteio)));
                jcs.setQtdeAcerto((byte)c.getInt(idxQtdeAcerto));

            }while (c.moveToNext());
        }

        return jogoConcursoSorteios;

    }

}
