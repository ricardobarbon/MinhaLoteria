package com.barbon.minhaloteria.controle;

import android.content.Context;

import com.barbon.minhaloteria.banco.JogoDAO;
import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.NumeroJogado;

import java.util.List;

/**
 * Created by Barbon on 28/05/2015.
 */
public class JogoControle {

    private static JogoControle instance;

    public JogoControle(){

    }

    public static JogoControle getInstance(){

        if (instance == null)
            instance = new JogoControle();

        return instance;
    }

    public List<Jogo> todosJogos(Context context){

        JogoDAO jogoDAO = new JogoDAO(context);
        LoteriaControle loteriaControle = LoteriaControle.getInstance();
        NumeroJogadoControle numeroJogadoControle = NumeroJogadoControle.getInstance();

        List<Loteria> loterias = loteriaControle.getLoterias(context);
        List<Jogo> jogos = jogoDAO.listarJogos();

        for (Jogo j: jogos){

            loteriaControle.definirLoteria(loterias, j.getLoteria());
            j.setNumerosJogados(numeroJogadoControle.recuperaNumeros(context, j));

        }

        return jogos;
    }
}
