package com.barbon.minhaloteria;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.controle.ConcursoControle;
import com.barbon.minhaloteria.controle.JogoControle;
import com.barbon.minhaloteria.controle.LoteriaControle;
import com.barbon.minhaloteria.controle.SorteioControle;
import com.barbon.minhaloteria.modelo.Concurso;
import com.barbon.minhaloteria.modelo.Jogo;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.Premio;
import com.barbon.minhaloteria.util.Internet;
import com.barbon.minhaloteria.util.WebService;
import com.barbon.minhaloteria.visao.JogoPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class PrincipalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jogos_detalhes);

        List<JogoPrincipal> jogos =  buscarJogos();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<JogoPrincipal> buscarJogos(){

        JogoControle jogoControle = JogoControle.getInstance();

        List<Jogo> jogos = jogoControle.todosJogos(this);

        List<JogoPrincipal> jogoPrincipals = new ArrayList<JogoPrincipal>();

        for (Jogo j: jogos){
            jogoPrincipals.add(new JogoPrincipal(this, j));
        }

        return jogoPrincipals;
    }


}
