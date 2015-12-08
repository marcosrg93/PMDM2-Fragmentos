package com.rubino.pmdm3_contacto2.fragmentos;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rubino.pmdm3_contacto2.R;
import com.rubino.pmdm3_contacto2.contacto.Contacto;
import com.rubino.pmdm3_contacto2.contacto.GestionContactos;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {


    private int dato;
    private List<Contacto> valores;
    private View viewFragment;
    private TextView tvNom,tvTel,tvTel2,tvTel3,tv4,tv5;
    private int position;

    public DetalleFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.fragment_detalle, container, false);
       init(viewFragment);

        Log.v("DATO", dato + "");
        Log.v("POS",position+"");
        return viewFragment;
    }


    public void init(View vista){
        valores = GestionContactos.getLista(this.getActivity());



        tvNom = (TextView) vista.findViewById(R.id.tvDNom);
        tvTel = (TextView) vista.findViewById(R.id.tvDTel);
        tvTel2 = (TextView) vista.findViewById(R.id.tvDTel2);
        tvTel3 = (TextView) vista.findViewById(R.id.tvDTel3);
        tv4 = (TextView) vista.findViewById(R.id.textView4);
        tv5 = (TextView) vista.findViewById(R.id.textView5);


    }

    public void setDato(int dato){
        this.dato = dato;
        this.position = this.dato;

        Contacto valor = valores.get(position);
        tvNom.setText(valores.get(position).getNombre());
        int numContactos = valor.getlTelf().size();
        if(numContactos>0) {
            if (numContactos == 1) {
                tvTel.setText(valor.getlTelf().get(0));
                tv4.setVisibility(View.INVISIBLE);
                tv5.setVisibility(View.INVISIBLE);
            } else  if (numContactos == 2){
                tvTel.setText(valor.getlTelf().get(0));
                tvTel2.setText(valor.getlTelf().get(1));
                tv5.setVisibility(View.INVISIBLE);
            }else  if (numContactos == 3){
                tvTel.setText(valor.getlTelf().get(0));
                tvTel2.setText(valor.getlTelf().get(1));
                tvTel3.setText(valor.getlTelf().get(2));
            }
        }
    }

}
