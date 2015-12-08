package com.rubino.pmdm3_contacto2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rubino.pmdm3_contacto2.fragmentos.DetalleFragment;

/**
 * Created by marco on 03/12/2015.
 */
public class Secundaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        int dato = getIntent().getExtras().getInt("datos");
        Log.v("DATO1", dato + "");
       DetalleFragment f = (DetalleFragment)getFragmentManager().
                findFragmentById(R.id.fragDetalle);
        f.setDato(dato);
    }
}