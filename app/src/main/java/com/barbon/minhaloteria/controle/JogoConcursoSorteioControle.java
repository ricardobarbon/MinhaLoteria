package com.barbon.minhaloteria.controle;

import android.content.Context;

import com.barbon.minhaloteria.banco.JogoConcursoSorteioDAO;
import com.barbon.minhaloteria.modelo.JogoConcurso;
import com.barbon.minhaloteria.modelo.JogoConcursoSorteio;

import java.util.List;

/**
 * Created by Barbon on 02/06/2015.
 */
public class JogoConcursoSorteioControle {

    private static JogoConcursoSorteioControle instance;

    private JogoConcursoSorteioControle(){

    }

    public static JogoConcursoSorteioControle getInstance(){

        if (instance == null)
            instance = new JogoConcursoSorteioControle();

        return instance;
    }

    public List<JogoConcursoSorteio> buscarJogoConcursoSorteio(Context context, JogoConcurso jogoConcurso){

        JogoConcursoSorteioDAO jogoConcursoSorteioDAO = new JogoConcursoSorteioDAO(context);

        return jogoConcursoSorteioDAO.buscaJogoConcursoSorteio(jogoConcurso);

    }
}
