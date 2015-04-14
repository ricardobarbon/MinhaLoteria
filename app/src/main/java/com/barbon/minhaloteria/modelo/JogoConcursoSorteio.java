package com.barbon.minhaloteria.modelo;

import com.barbon.minhaloteria.banco.DataBaseHelper;

/**
 * Created by Barbon on 22/02/2015.
 */
public class JogoConcursoSorteio {

    public static String[] colunas = new String[]{JogosConcursosSorteios.ID_JOGOCONCURSO, JogosConcursosSorteios.ID_SORTEIO, JogosConcursosSorteios.ID_PREMIO, JogosConcursosSorteios.QTDE_ACERTO};

    private JogoConcurso jogoConcurso;
    private Sorteio sorteio;
    private byte qtdeAcerto;
    private Premio premio;

    public JogoConcurso getJogoConcurso() {
        return jogoConcurso;
    }

    public void setJogoConcurso(JogoConcurso jogoConcurso) {
        this.jogoConcurso = jogoConcurso;
    }

    public Sorteio getSorteio() {
        return sorteio;
    }

    public void setSorteio(Sorteio sorteio) {
        this.sorteio = sorteio;
    }

    public byte getQtdeAcerto() {
        return qtdeAcerto;
    }

    public void setQtdeAcerto(byte qtdeAcerto) {
        this.qtdeAcerto = qtdeAcerto;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    public static final class JogosConcursosSorteios{

        public static final String ID_JOGOCONCURSO = DataBaseHelper.COLUNA_ID_JOGOCONCURSO;
        public static final String ID_SORTEIO = DataBaseHelper.COLUNA_ID_SORTEIO;
        public static final String ID_PREMIO = DataBaseHelper.COLUNA_ID_PREMIO;
        public static final String QTDE_ACERTO = DataBaseHelper.COLUNA_QTDE_ACERTO;

    }

    @Override
    public String toString(){
        return "JOGOSORTEIO ==> Jogo: " + jogoConcurso + ", Sorteio: " + sorteio + ", Premio: " + premio + ", Qtde Acerto: " + qtdeAcerto;
    }
}
