package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;

import com.barbon.minhaloteria.modelo.JogoConcursoSorteio;

/**
 * Created by Barbon on 18/03/2015.
 */
public class JogoConcursoSorteioDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_JOGOCONCURSOSORTEIO;

    public JogoConcursoSorteioDAO(Context context){
        super(context);
    }

    public long salvar(JogoConcursoSorteio jogo){

        long id = jogo.getId();

        if (id != 0)
            atualizar(jogo);
        else
            id = inserir(jogo);

        return id;
    }

    public long inserir(Jogo jogo){
        ContentValues values = new ContentValues();

        values.put(Jogo.Jogos.DESCRICAO, jogo.getDescricao());
        values.put(Jogo.Jogos.JOGO_PERMANENTE, (jogo.isJogoPermanente()) ? 1:0);
        values.put(Jogo.Jogos.ID_LOTERIA, jogo.getLoteria().getId());

        return inserir(NOME_TABELA, values);
    }

    public int atualizar(Jogo jogo){
        ContentValues values = new ContentValues();

        values.put(Jogo.Jogos.DESCRICAO, jogo.getDescricao());
        values.put(Jogo.Jogos.JOGO_PERMANENTE, (jogo.isJogoPermanente()) ? 1:0);
        values.put(Jogo.Jogos.ID_LOTERIA, jogo.getLoteria().getId());

        String _id = String.valueOf(jogo.getId());
        String where = Jogo.Jogos._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    public int deletar(long id){
        String where = Jogo.Jogos._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

}
