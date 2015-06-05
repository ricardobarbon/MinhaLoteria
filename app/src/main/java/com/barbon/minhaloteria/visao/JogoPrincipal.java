package com.barbon.minhaloteria.visao;

import android.content.Context;

import com.barbon.minhaloteria.controle.ConcursoControle;
import com.barbon.minhaloteria.controle.JogoConcursoControle;
import com.barbon.minhaloteria.controle.JogoConcursoSorteioControle;
import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.JogoConcurso;

/**
 * Created by Barbon on 28/05/2015.
 */
public class JogoPrincipal {
    public Jogo jogo;
    public Concurso ultimaAposta;
    public JogoConcurso ultimoSorteio;

    public JogoPrincipal(Context context, Jogo jogo){

        ConcursoControle concursoControle = ConcursoControle.getInstance();

        this.jogo = jogo;

        this.ultimaAposta = concursoControle.getUltimoConcurso(context, jogo);

        if (this.ultimaAposta != null) {

            JogoConcursoControle jogoConcursoControle = JogoConcursoControle.getInstance();

            this.ultimoSorteio = jogoConcursoControle.ultimoSorteio(context, jogo);

            if (this.ultimoSorteio != null){

                JogoConcursoSorteioControle jogoConcursoSorteioControle = JogoConcursoSorteioControle.getInstance();

                this.ultimoSorteio.setJogoConcursoSorteios(jogoConcursoSorteioControle.buscarJogoConcursoSorteio(context, this.ultimoSorteio));

            }
        }
    }
}
