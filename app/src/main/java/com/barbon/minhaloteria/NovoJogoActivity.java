package com.barbon.minhaloteria;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.barbon.minhaloteria.controle.LoteriaControle;
import com.barbon.minhaloteria.controle.NumeroJogadoControle;
import com.barbon.minhaloteria.modelo.Loteria;
import com.barbon.minhaloteria.modelo.NumeroJogado;
import com.barbon.minhaloteria.visao.LoteriaAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NovoJogoActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private CheckBox chkPermanente;
    private TextView txtNumerosSelecionados;
    private LinearLayout linearLayoutNumerosPrincipal;
    private EditText eTxtDescricao;
    private Spinner spinner;

    private List<Loteria> loterias;
    private List<NumeroJogado> numeroJogados;
    private List<NumeroJogado> numerosMarcados;
    private Loteria loteria;

    private LoteriaControle loteriaControle;
    private NumeroJogadoControle numeroJogadoControle;
    private NumeroJogadoControle.ComparatorByNumero comparatorByNumero;

    private MenuItem menuItemSalvar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogo);

        loteriaControle = LoteriaControle.getInstance();
        numeroJogadoControle = NumeroJogadoControle.getInstance();
        comparatorByNumero = new NumeroJogadoControle.ComparatorByNumero();

        loterias = LoteriaControle.getLoterias(this);

        spinner = (Spinner) findViewById(R.id.spinnerLoteriaNovoJogo);

        spinner.setAdapter(new LoteriaAdapter(this, this, R.layout.loteria_spinner, loterias));

        spinner.setOnItemSelectedListener(this);

        chkPermanente = (CheckBox) findViewById(R.id.checkPermanente);
        txtNumerosSelecionados = (TextView) findViewById(R.id.txtNumerosSelecionados);
        linearLayoutNumerosPrincipal = (LinearLayout) findViewById(R.id.layoutNumerosPrincipal);
        eTxtDescricao = (EditText) findViewById(R.id.eTxtDescricao);
        menuItemSalvar = (MenuItem) findViewById(R.id.mnuSalvarJogo);

        loteria = loterias.get(spinner.getSelectedItemPosition());

        iniciarTelaLoteria(loteria);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_novo_jogo, menu);
        menuItemSalvar = menu.findItem(R.id.mnuSalvarJogo);
        menuItemSalvar.setVisible(false);

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

        eTxtDescricao.setText("");

        numerosMarcados = new ArrayList<NumeroJogado>();
        setTxtNumerosSelecionados();

        chkPermanente.setChecked(false);

        numeroJogados = numeroJogadoControle.geraListaNumerosPossiveis(loteria);

        linearLayoutNumerosPrincipal.removeAllViews();

        LinearLayout ll = new LinearLayout(this);

        for (int i = 1; i <= numeroJogados.size(); i++){
            if (i % 5 == 1){
                ll = criarLinearLayoutNumeros();
                linearLayoutNumerosPrincipal.addView(ll);
            }
            ll.addView(criarTextView(numeroJogados.get(i - 1)));
        }

    }

    private void setTxtNumerosSelecionados(){

        switch (numerosMarcados.size()){
            case 0:
                txtNumerosSelecionados.setText(" ");
                break;
            case 1:
                txtNumerosSelecionados.setText(numerosMarcados.size() + " " + getResources().getString(R.string.numero_selecionado));
                break;
            default:
                txtNumerosSelecionados.setText(numerosMarcados.size() + " " + getResources().getString(R.string.numeros_selecionados));
                break;
        }

    }

    private void habilitaDesabilitaSalvar(){

        menuItemSalvar.setVisible(numerosMarcados.size() >= loteria.getQtdeMinMarcacao());

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private TextView criarTextView(NumeroJogado numero){
        String is = String.valueOf(numero.getNumero());
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
        txt.setText((is.length() == 1 ? "0" : "") + is);
        txt.setElevation(10);
        txt.setClickable(true);
        txt.setTag(numero);

        txt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Collections.sort(numerosMarcados, comparatorByNumero);

                NumeroJogado numeroJogado = (NumeroJogado) v.getTag();

                if (Collections.binarySearch(numerosMarcados, numeroJogado, comparatorByNumero) >= 0) {

                    v.setBackgroundResource(loteriaControle.recuperarBackground(loterias.get(spinner.getSelectedItemPosition())));
                    numerosMarcados.remove(numeroJogado);

                } else {

                    if (!loteriaControle.podeContinuarMarcando(loteria, numerosMarcados.size())) {
                        Toast.makeText(getContexto(), R.string.qtde_maxima, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    v.setBackgroundResource(loteriaControle.recuperarBackgroundMarcado(loterias.get(spinner.getSelectedItemPosition())));
                    numerosMarcados.add(numeroJogado);
                }

                habilitaDesabilitaSalvar();
                setTxtNumerosSelecionados();
            }
        });

        return txt;
    }

    private Context getContexto(){
        return this;
    }

    private LinearLayout criarLinearLayoutNumeros(){

        LinearLayout ll = new LinearLayout(this);

        ll.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        ll.setGravity(Gravity.CENTER);
        ll.setLayoutParams(llParams);

        return ll;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (new LoteriaControle.DescricaoComparator().compare(loteria, loterias.get(position)) != 0) {
            loteria = loterias.get(position);
            iniciarTelaLoteria(loteria);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
