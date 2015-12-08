package com.rubino.pmdm3_contacto2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rubino.pmdm3_contacto2.contacto.AdaptadorContacto;
import com.rubino.pmdm3_contacto2.contacto.Contacto;
import com.rubino.pmdm3_contacto2.contacto.GestionContactos;
import com.rubino.pmdm3_contacto2.contacto.OrdenaNombresAsc;
import com.rubino.pmdm3_contacto2.contacto.OrdenaNombresDes;
import com.rubino.pmdm3_contacto2.fragmentos.DetalleFragment;

import java.util.Collections;
import java.util.List;


public class  Principal extends AppCompatActivity implements OnFragmentoInteraccionListener {

    private AdaptadorContacto ac;
    private List<Contacto> lContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        lContactos = GestionContactos.getLista(this);

        ac = new AdaptadorContacto(this, R.layout.elementos_lv, lContactos);

    }


    @Override
    public void onInteraccion(int datos) {
        DetalleFragment fragmento = (DetalleFragment)getFragmentManager().findFragmentById(R.id.fragmentdetalle);
        if (fragmento == null || !fragmento.isInLayout())  {
            //Vertical
            Intent i = new Intent(this, Secundaria.class);
            i.putExtra("datos", datos);
            startActivity(i);
        }
        else
        {
            //Horizontal
            fragmento.setDato(datos);
        }
    }




    //---------------------------------------------------------------------------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

       /* SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE );
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.mn_ordenaMayor) {
            //ordenaNombresAsc();
            ac.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.mn_ordenaMenor) {
            //ordenaNombresDes();
            ac.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    //---------------------------------------------------------------------------------------------------------------------//
    public void ordenaNombresAsc(){
        Collections.sort(lContactos, new OrdenaNombresAsc());
    }

    public void ordenaNombresDes(){
        Collections.sort(lContactos, new OrdenaNombresDes());
    }


}
