package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.NumeroJogado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barbon on 12/03/2015.
 */
public class JogoDAO extends DbDAO {

    public static final String NOME_TABELA = DataBaseHelper.TABELA_JOGO;

    public JogoDAO(Context context){
        super(context);
    }

    public void salvar(Jogo jogo){
        long idNumeroJogado;
        long id = jogo.getId();

        if (id != 0)

            atualizar(jogo);

        else {

            database.beginTransaction();

            try{
                id = inserir(jogo);
                jogo.setId(id);

                NumeroJogadoDAO numeroJogadoDAO = new NumeroJogadoDAO(getmContext());

                for (NumeroJogado numeroJogado: jogo.getNumerosJogados()){
                    idNumeroJogado = numeroJogadoDAO.salvar(numeroJogado);
                    numeroJogado.setId(idNumeroJogado);
                }

                database.setTransactionSuccessful();
            }
            catch (Exception e){
                jogo.setId(0);
                Log.e(getmContext().getApplicationInfo().name, "Erro inserir jogo ---> " + e.getMessage());
            }
            finally {
                database.endTransaction();
            }

        }

    }

    private long inserir(Jogo jogo){
        ContentValues values = new ContentValues();

        values.put(Jogo.Jogos.DESCRICAO, jogo.getDescricao());
        values.put(Jogo.Jogos.JOGO_PERMANENTE, (jogo.isJogoPermanente()) ? 1:0);
        values.put(Jogo.Jogos.ID_LOTERIA, jogo.getLoteria().getId());

        return inserir(NOME_TABELA, values);
    }

    private int atualizar(Jogo jogo){
        ContentValues values = new ContentValues();

        values.put(Jogo.Jogos.DESCRICAO, jogo.getDescricao());
        values.put(Jogo.Jogos.JOGO_PERMANENTE, (jogo.isJogoPermanente()) ? 1:0);
        values.put(Jogo.Jogos.ID_LOTERIA, jogo.getLoteria().getId());

        String _id = String.valueOf(jogo.getId());
        String where = Jogo.Jogos._ID + "=?";
        String[] whereArgs = new String[]{_id};

        return atualizar(NOME_TABELA, values, where, whereArgs);
    }

    private int deletar(long id){
        String where = Jogo.Jogos._ID + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        return deletar(NOME_TABELA, where, whereArgs);
    }

    public Jogo buscarJogo(long id){

        Cursor c = database.query(true, NOME_TABELA, Jogo.colunas, Jogo.Jogos._ID + "=" + id, null, null, null, null, null);

        if (c.getCount() > 0){
            c.moveToFirst();
            Jogo jogo = new Jogo();
            Loteria loteria = new Loteria();
            jogo.setLoteria(loteria);

            jogo.setId(c.getLong(0));
            jogo.setDescricao(c.getString(1));
            jogo.setJogoPermanente(c.getLong(2) != 0);
            loteria.setId(c.getLong(c.getColumnIndex(Jogo.Jogos.ID_LOTERIA)));

            return jogo;
        }

        return null;
    }

    public List<Jogo> listarJogos(){

        Cursor c = database.query(NOME_TABELA, Jogo.colunas, null, null, null, null, null, null);

        List<Jogo> jogos = new ArrayList<Jogo>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(Jogo.Jogos._ID);
            int idxDescricao = c.getColumnIndex(Jogo.Jogos.DESCRICAO);
            int idxJogoPermanente = c.getColumnIndex(Jogo.Jogos.JOGO_PERMANENTE);
            int idxIdLoteria = c.getColumnIndex(Jogo.Jogos.ID_LOTERIA);

            do{
                Jogo j = new Jogo();
                Loteria l = new Loteria();
                j.setLoteria(l);

                jogos.add(j);

                j.setId(c.getLong(idxId));
                j.setDescricao(c.getString(idxDescricao));
                j.setJogoPermanente(c.getLong(idxJogoPermanente)!=0);
                l.setId(c.getLong(idxIdLoteria));

            }while (c.moveToNext());
        }

        return jogos;
    }

    public List<Jogo> listarJogos(Loteria loteria){

        Cursor c = database.query(NOME_TABELA, Jogo.colunas, Jogo.Jogos.ID_LOTERIA + " = " + loteria.getId(), null, null, null, null, null);

        List<Jogo> jogos = new ArrayList<Jogo>();

        if (c.moveToFirst()){

            int idxId = c.getColumnIndex(Jogo.Jogos._ID);
            int idxDescricao = c.getColumnIndex(Jogo.Jogos.DESCRICAO);
            int idxJogoPermanente = c.getColumnIndex(Jogo.Jogos.JOGO_PERMANENTE);
            int idxIdLoteria = c.getColumnIndex(Jogo.Jogos.ID_LOTERIA);

            do{
                Jogo j = new Jogo();
                Loteria l = new Loteria();
                j.setLoteria(l);

                jogos.add(j);

                j.setId(c.getLong(idxId));
                j.setDescricao(c.getString(idxDescricao));
                j.setJogoPermanente(c.getLong(idxJogoPermanente)!=0);
                l.setId(c.getLong(idxIdLoteria));

            }while (c.moveToNext());
        }

        return jogos;
    }
}
