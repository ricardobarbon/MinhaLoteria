package com.barbon.minhaloteria.visao;

import android.content.Context;

import com.barbon.minhaloteria.controle.ConcursoControle;
import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;

/**
 * Created by Barbon on 28/05/2015.
 */
public class JogoPrincipal {
    public Jogo jogo;
    public Concurso ultimaAposta;
    public Concurso ultimoSorteio;

    public JogoPrincipal(Context context, Jogo jogo){

        ConcursoControle concursoControle = ConcursoControle.getInstance();

        this.jogo = jogo;

        this.ultimaAposta = concursoControle.getUltimoConcurso(context, jogo);

        this.ultimoSorteio = concursoControle.getUltimoConcursoSorteio(context, jogo);

    }
}
