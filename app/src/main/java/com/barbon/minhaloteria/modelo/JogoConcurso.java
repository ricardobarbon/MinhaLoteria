package com.barbon.minhaloteria.modelo;

import android.provider.BaseColumns;

import com.barbon.minhaloteria.banco.DataBaseHelper;

import java.util.List;

/**
 * Created by Barbon on 22/02/2015.
 */
public class JogoConcurso {

    public static String[] colunas = new String[]{JogosConcursos._ID, JogosConcursos.ID_JOGO, JogosConcursos.ID_CONCURSO};

    private int id;
    private Jogo jogo;
    private Concurso concurso;
    private List<JogoConcursoSorteio> jogoConcursoSorteios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<JogoConcursoSorteio> getJogoConcursoSorteios() {
        return jogoConcursoSorteios;
    }

    public void setJogoConcursoSorteios(List<JogoConcursoSorteio> jogoConcursoSorteios) {
        this.jogoConcursoSorteios = jogoConcursoSorteios;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public static final class JogosConcursos implements BaseColumns {

        public static final String ID_JOGO = DataBaseHelper.COLUNA_ID_JOGO;
        public static final String ID_CONCURSO = DataBaseHelper.COLUNA_ID_CONCURSO;

    }

    @Override
    public String toString(){
        return "JOGOCONCURSO ==> Id: " + id + ", Jogo: " + jogo + ", Concurso: " + concurso;
    }
}
