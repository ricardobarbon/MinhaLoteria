package com.barbon.minhaloteria.controle;

import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.Premio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barbon on 21/04/2015.
 */
public class PremioControle {

    private static PremioControle instance;

    private PremioControle(){

    }

    public static PremioControle getInstance(){

        if (instance == null)
            instance = new PremioControle();

        return instance;
    }

    protected List<Premio> criaPremiosLotofacil(Loteria loteria){
        return criaLista(11, 15, loteria);
    }

    protected List<Premio> criaPremiosQuina(Loteria loteria){
        return criaLista(3,5, loteria);
    }

    protected List<Premio> criaPremiosMegaSena(Loteria loteria){
        return criaLista(4,6, loteria);
    }

    private List<Premio> criaLista(int menosAcerto, int maiorAcerto, Loteria loteria){
        List<Premio> l = new ArrayList<Premio>();
        int c = maiorAcerto - menosAcerto + 1;

        for (int i = menosAcerto; i <= maiorAcerto;i++){

            Premio p = new Premio();

            p.setQtdeAcerto((byte)i);
            p.setNivel((byte)c);
            p.setLoteria(loteria);

            l.add(p);

            c--;
        }

        return l;
    }
}
