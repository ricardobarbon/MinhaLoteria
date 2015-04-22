package com.barbon.minhaloteria;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.controle.LoteriaControle;
import com.barbon.minhaloteria.modelo.Loteria;


public class PrincipalActivity extends ActionBarActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        LoteriaControle loteriaControle = LoteriaControle.getInstance(this);

        loteriaControle.criarLoterias(this);

        t = (TextView)findViewById(R.id.texto);

        //t.setText(loteriaControle.getLoterias().size());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String s = "";
        LoteriaDAO loteriaDAO = new LoteriaDAO(this);

        for (Loteria l: loteriaDAO.listarLoterias()){
            s = s + l.getDescricao() + ";";
        }

        t.setText(s);

        Toast.makeText(this, "teste", Toast.LENGTH_LONG).show();
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
}
