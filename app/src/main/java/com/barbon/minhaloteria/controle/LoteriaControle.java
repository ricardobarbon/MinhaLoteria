package com.barbon.minhaloteria.controle;

import com.barbon.minhaloteria.App;
import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.modelo.Loteria;

import java.util.Collections;
import java.util.List;

/**
 * Created by Barbon on 20/04/2015.
 */
public class LoteriaControle {

    public static final String LOTOFACIL = "LOTOFÁCIL";
    public static final String MEGASENA = "MEGA-SENA";
    public static final String QUINA = "QUINA";

    private static LoteriaControle instance;

    private List<Loteria> loterias;

    private LoteriaControle(){
        LoteriaDAO loteriaDAO = new LoteriaDAO(App.getContext());

        loterias = loteriaDAO.listarLoterias();
    }

    public static LoteriaControle getInstance(){

        if (instance == null)
            instance = new LoteriaControle();

        return instance;
    }

    public void criarLoterias(){

    }

    public List<Loteria> getLoterias() {
        return loterias;
    }

    public void setLoterias(List<Loteria> loterias) {
        this.loterias = loterias;
    }
}
