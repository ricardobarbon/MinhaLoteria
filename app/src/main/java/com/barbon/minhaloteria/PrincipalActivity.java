package com.barbon.minhaloteria;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.barbon.minhaloteria.banco.LoteriaDAO;
import com.barbon.minhaloteria.controle.ConcursoControle;
import com.barbon.minhaloteria.controle.LoteriaControle;
import com.barbon.minhaloteria.controle.SorteioControle;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.Premio;
import com.barbon.minhaloteria.util.Internet;
import com.barbon.minhaloteria.util.WebService;


public class PrincipalActivity extends ActionBarActivity {

    private ProgressDialog dialog;
    TextView t;
    LoteriaControle loteriaControle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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

    private class Inicializador extends AsyncTask<Void, String ,Void >{

        Context context;

        private Inicializador(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = ProgressDialog.show(context, "Inicializando...", "Inicializando..", true, true);

        }

        @Override
        protected Void doInBackground(Void... params) {

            publishProgress("Verificando cadastro de loterias...");
            loteriaControle = LoteriaControle.getInstance();
            loteriaControle.criarLoterias(context);

            if (!Internet.existeConexao(context)) {

                Toast.makeText(context, "Sem internet...", Toast.LENGTH_LONG);

            }
            else{

                ConcursoControle.getInstance().;

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            dialog.dismiss();

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            dialog.setMessage(values[0]);

        }
    }
}
