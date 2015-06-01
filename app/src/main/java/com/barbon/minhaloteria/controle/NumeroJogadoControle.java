package com.barbon.minhaloteria.controle;

import android.content.Context;

import com.barbon.minhaloteria.banco.NumeroJogadoDAO;
import com.barbon.minhaloteria.banco.NumeroSorteadoDAO;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.NumeroJogado;

import java.util.List;

/**
 * Created by Barbon on 31/05/2015.
 */
public class NumeroJogadoControle {

    private static NumeroJogadoControle instance;

    public NumeroJogadoControle(){

    }

    public static NumeroJogadoControle getInstance(){

        if (instance == null)
            instance = new NumeroJogadoControle();

        return instance;
    }

    public List<NumeroJogado> recuperaNumeros(Context context, Jogo jogo){

        NumeroJogadoDAO numeroJogadoDAO = new NumeroJogadoDAO(context);

        return numeroJogadoDAO.buscarNumeros(jogo);

    }
}
