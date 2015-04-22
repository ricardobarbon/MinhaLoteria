package com.barbon.minhaloteria.controle;

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

    protected List<Premio> criaPremiosLotofacil(){
        return criaLista(11, 15);
    }

    protected List<Premio> criaPremiosQuina(){
        return criaLista(3,5);
    }

    protected List<Premio> criaPremiosMegaSena(){
        return criaLista(4,6);
    }

    private List<Premio> criaLista(int menosAcerto, int maiorAcerto){
        List<Premio> l = new ArrayList<Premio>();
        int c = maiorAcerto - menosAcerto + 1;

        for (int i = menosAcerto; i <= maiorAcerto;i++){

            Premio p = new Premio();

            p.setQtdeAcerto((byte)i);
            p.setNivel((byte)c);

            l.add(p);

            c--;
        }

        return l;
    }
}
