package com.barbon.minhaloteria;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.barbon.minhaloteria.controle.LoteriaControle;
import com.barbon.minhaloteria.controle.NumeroJogadoControle;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.NumeroJogado;
import com.barbon.minhaloteria.visao.LoteriaAdapter;

import java.util.List;

public class NovoJogoActivity extends ActionBarActivity {

    private CheckBox chkPermanente;
    private TextView txtNumerosSelecionados;
    private LinearLayout linearLayoutNumerosPrincipal;

    private List<Loteria> loterias;
    private List<NumeroJogado> numeroJogados;

    LoteriaControle loteriaControle;
    NumeroJogadoControle numeroJogadoControle;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogo);

        loteriaControle = LoteriaControle.getInstance();
        numeroJogadoControle = NumeroJogadoControle.getInstance();

        loterias = LoteriaControle.getLoterias(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerLoteriaNovoJogo);

        spinner.setAdapter(new LoteriaAdapter(this, this, R.layout.loteria_spinner, loterias));

        chkPermanente = (CheckBox) findViewById(R.id.checkPermanente);
        txtNumerosSelecionados = (TextView) findViewById(R.id.txtNumerosSelecionados);
        linearLayoutNumerosPrincipal = (LinearLayout) findViewById(R.id.layoutNumerosPrincipal);

        iniciarTelaLoteria(loterias.get(spinner.getSelectedItemPosition()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novo_jogo, menu);
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

    private void iniciarTelaLoteria(Loteria loteria){

        numeroJogados = numeroJogadoControle.geraListaNumerosPossiveis(loteria);

        linearLayoutNumerosPrincipal.removeAllViews();

        LinearLayout ll = new LinearLayout(this);

        for (int i = 1; i <= numeroJogados.size(); i++){
            if (i % 5 == 1){
                ll = criarLinearLayoutNumeros();
                linearLayoutNumerosPrincipal.addView(ll);
            }
            ll.addView(criarTextView(i, loteria));
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private TextView criarTextView(int i, Loteria loteria){
        String is = String.valueOf(i);

        TextView txt = new TextView(this);

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

        txt.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        txt.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)txt.getLayoutParams();
        lp.setMargins(margin, margin, margin, margin);
        txt.setBackgroundResource(loteriaControle.recuperarBackground(loteria));
        txt.setPadding(padding, padding, padding, padding);
        txt.setTextColor(getResources().getColor(loteriaControle.recuperarCor(loteria)));
        txt.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        txt.setText((is.length() == 1? "0":"") + is);
        txt.setElevation(10);

        return txt;

        /*LinearLayout ll = (LinearLayout)findViewById(R.id.layoutNumeros);

        ll.removeAllViews();

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

        txts = new TextView[5];

        for (int i = 1; i < numeroJogados.size(); i++){
            txts[i] = new TextView(this);
            txts[i].setTextAppearance(this, android.R.style.TextAppearance_Medium);
            txts[i].setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)txts[i].getLayoutParams();
            lp.setMargins(margin, margin, margin, margin);
            txts[i].setBackgroundResource(R.drawable.back_lotofacil);
            txts[i].setPadding(padding, padding, padding, padding);
            txts[i].setTextColor(getResources().getColor(R.color.lotofacil));
            txts[i].setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            txts[i].setText("33");
            txts[i].setElevation(10);
            ll.addView(txts[i]);
        }*/
    }

    private LinearLayout criarLinearLayoutNumeros(){

        LinearLayout ll = new LinearLayout(this);

        ll.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        ll.setGravity(Gravity.CENTER);
        ll.setLayoutParams(llParams);

        return ll;
    }
}
