package com.barbon.minhaloteria.controle;

import android.content.Context;

import com.barbon.minhaloteria.banco.JogoConcursoDAO;
import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.JogoConcurso;

/**
 * Created by Barbon on 01/06/2015.
 */
public class JogoConcursoControle {

    private static JogoConcursoControle instance;

    private JogoConcursoControle(){

    }

    public static JogoConcursoControle getInstance(){

        if (instance == null)
            instance = new JogoConcursoControle();

        return instance;
    }

    public JogoConcurso ultimoSorteio(Context context, Jogo jogo){

        ConcursoControle concursoControle = ConcursoControle.getInstance();
        JogoConcursoDAO jogoConcursoDAO = new JogoConcursoDAO(context);

        Concurso concurso = concursoControle.getUltimoConcursoSorteio(context, jogo);

        if (concurso == null)
            return null;
        else
            return jogoConcursoDAO.buscarJogoConcurso(jogo, concurso);

    }
}
