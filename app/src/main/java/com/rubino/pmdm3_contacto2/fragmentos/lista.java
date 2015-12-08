package com.rubino.pmdm3_contacto2.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rubino.pmdm3_contacto2.OnFragmentoInteraccionListener;
import com.rubino.pmdm3_contacto2.contacto.AdaptadorContacto;
import com.rubino.pmdm3_contacto2.R;
import com.rubino.pmdm3_contacto2.contacto.Contacto;
import com.rubino.pmdm3_contacto2.contacto.GestionContactos;
import com.rubino.pmdm3_contacto2.filtros.OnScrollUpDownListener;

import java.util.List;

public class lista extends Fragment {

    private List<Contacto> lContactos;
    private FloatingActionButton fab;
    private ListView listView;
    private AdaptadorContacto ac;
    private OnFragmentoInteraccionListener listener;

    public lista() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentlista = inflater.inflate(R.layout.fragment_lista, container, false);
                init(fragmentlista);
        // Inflate the layout for this fragment
        return fragmentlista;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentoInteraccionListener){
            listener = (OnFragmentoInteraccionListener)context;
        }else {
            throw new ClassCastException("Solo acepto OnFragmentoInteraccionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OnFragmentoInteraccionListener){
            listener = (OnFragmentoInteraccionListener)activity;
        }else {
            throw new ClassCastException("Solo acepto OnFragmentoInteraccionListener");
        }
    }

    //Inicializamos todos los componentes que llamaremos en el onCreate
    private void init(View fragmentlista) {
        //Floating Action Button
        fab = (FloatingActionButton) fragmentlista.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ac.dgInsert();
            }
        });

        //Comprobamos la posicion para que se muestre o no
        OnScrollUpDownListener.Action scrollAction = new OnScrollUpDownListener.Action() {
            @Override
            public void up() {
                fab.hide();
            }

            @Override
            public void down() {
                fab.show();
            }
        };

        listView = (ListView) fragmentlista.findViewById(R.id.lvContactos);
        lContactos = GestionContactos.getLista(this.getActivity());

        ac = new AdaptadorContacto(this.getActivity(), R.layout.elementos_lv, lContactos);

        listView.setTextFilterEnabled(true);

        listView.setAdapter(ac);

        listView.setTag(lContactos);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent i = new Intent(getActivity(), Secundaria.class);
                startActivity(i);*/
                listener.onInteraccion(position);

                Log.v("POSLV", position + "");
            }
        });

        //Comprueba la posición en el listview para ocultarlo o no según la acción realizada
        listView.setOnScrollListener(new OnScrollUpDownListener(listView, 8, scrollAction));

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.contextual, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        long id = item.getItemId();
        AdapterView.AdapterContextMenuInfo vistaInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int posicion = vistaInfo.position;

        if(id==R.id.mn_editar){
            ac.dgEdit(posicion);
            return true;
        } else if(id==R.id.mn_borrar){
            ac.borrar(posicion);
            return true;
        }
        return super.onContextItemSelected(item);
    }





}
