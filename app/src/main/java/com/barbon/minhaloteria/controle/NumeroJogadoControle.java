package com.barbon.minhaloteria.controle;

import android.content.Context;

import com.barbon.minhaloteria.banco.NumeroJogadoDAO;
import com.barbon.minhaloteria.banco.NumeroSorteadoDAO;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.NumeroJogado;

import java.util.ArrayList;
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

    public List<NumeroJogado> geraListaNumerosPossiveis(Loteria loteria){

        int qtde = LoteriaControle.getInstance().recuperarQtdeNumeros(loteria);

        List<NumeroJogado> numeroJogados = new ArrayList<NumeroJogado>();

        for (int i = 1; i <= qtde; i++){
            NumeroJogado numeroJogado = new NumeroJogado();

            numeroJogado.setNumero((byte)i);

            numeroJogados.add(numeroJogado);
        }

        return numeroJogados;
    }
}
