package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.JogoConcurso;

/**
 * Created by Barbon on 18/03/2015.
 */
public class JogoConcursoDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_JOGOCONSURSO;

    public JogoConcursoDAO(Context context){
        super(context);
    }

    public long salvar(JogoConcurso jogoConcurso){

        long id = jogoConcurso.getId();

        if (id != 0)
            atualizar(jogoConcurso);
        else
            id = inserir(jogoConcurso);

        return id;
    }

    public long inserir(JogoConcurso jogoConcurso){
        ContentValues values = new ContentValues();

        values.put(JogoConcurso.JogosConcursos.ID_JOGO, jogoConcurso.getJogo().getId());
        values.put(JogoConcurso.JogosConcursos.ID_CONCURSO, jogoConcurso.getConcurso().getId());

        return inserir(NOME_TABELA, values);
    }

    public int atualizar(JogoConcurso jogoConcurso){
        ContentValues values = new ContentValues();

        values.put(JogoConcurso.JogosConcursos.ID_JOGO, jogoConcurso.getJogo().getId());
        values.put(JogoConcurso.JogosConcursos.ID_CONCURSO, jogoConcurso.getConcurso().getId());

        String _id = String.valueOf(jogoConcurso.getId());
        String where = JogoConcurso.JogosConcursos._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = JogoConcurso.JogosConcursos._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public JogoConcurso buscarJogoConcurso(Jogo jogo, Concurso concurso){

        Cursor c = database.query(true, NOME_TABELA, JogoConcurso.colunas, JogoConcurso.JogosConcursos.ID_CONCURSO + "=" + concurso.getId() +
                                   " and " + JogoConcurso.JogosConcursos.ID_JOGO + "=" + jogo.getId(), null, null, null, null, null);

        if (c.getCount() > 0){
            c.moveToFirst();
            JogoConcurso jogoConcurso = new JogoConcurso();

            jogoConcurso.setId(c.getInt(0));
            jogoConcurso.setJogo(jogo);
            jogoConcurso.setConcurso(concurso);

            return jogoConcurso;
        }

        return null;
    }

}
