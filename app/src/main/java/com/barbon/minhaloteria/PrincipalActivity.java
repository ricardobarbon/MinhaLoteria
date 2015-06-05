package com.barbon.minhaloteria;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.barbon.minhaloteria.visao.JogoAdapterPrincipal;
import com.barbon.minhaloteria.visao.JogoPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class PrincipalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);

        List<JogoPrincipal> jogos =  buscarJogos();

        ListView lv = (ListView) findViewById(R.id.lista);

        if (jogos.size() == 0){

            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativePrincipal);

            lv.setVisibility(View.GONE);

            TextView t = new TextView(this);
            t.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            t.setText(R.string.jogoNaoCadastrado);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                             RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);

            relativeLayout.addView(t, lp);

        }else {

            lv.setAdapter(new JogoAdapterPrincipal(this, jogos));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnuNovoJogo:
                Intent intent = new Intent("com.barbon.minhaLoteria.novoJogo");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
