package com.barbon.minhaloteria.visao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.barbon.minhaloteria.R;
import com.barbon.minhaloteria.modelo.Loteria;

import java.util.List;

/**
 * Created by Barbon on 11/06/2015.
 */
public class LoteriaAdapter extends ArrayAdapter<Loteria> {
    private List<Loteria> loterias;
    LayoutInflater inflater;

    public LoteriaAdapter(Activity activity, Context context, int resource, List<Loteria> objects) {
        super(context, resource, objects);

        this.loterias = objects;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewCustom(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getViewCustom(position, convertView, parent);
    }

    private View getViewCustom(int position, View converView, ViewGroup parent){
        Loteria loteria = loterias.get(position);

        View spinView = inflater.inflate(R.layout.loteria_spinner, parent, false);

        ImageView i1 = (ImageView) spinView.findViewById(R.id.imgLoteria);

        i1.setImageResource(loteria.getImagem());

        return spinView;
    }
}
