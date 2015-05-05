package com.barbon.minhaloteria.controle;

import android.os.AsyncTask;

import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.util.WebService;

import java.net.URL;

/**
 * Created by Barbon on 27/04/2015.
 */
public class SorteioControle{

    private static SorteioControle instance;

    private SorteioControle(){

    }

    public static SorteioControle getInstance(){

        if (instance == null)
            instance = new SorteioControle();

        return instance;
    }

}
