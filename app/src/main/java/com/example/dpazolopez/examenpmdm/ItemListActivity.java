package com.example.dpazolopez.examenpmdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


//12 IMPLEMENTAMOS UNA INTERFAZ DEFINIDA EN EL FRAGMENT PARA SABER QUE HACER CUANDO EL FRAGMENT NOS DIGA CERRAR
public class ItemListActivity extends AppCompatActivity
        implements ItemListFragment.Callbacks, ItemDetailFragment.MyFragmentListener {


    /** Esta variable controla si esta mostrando uno o dos paneles */
    private boolean mTwoPane=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_app_bar);
        /** layout que carga la actividad */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**.Si hay panel de la derecha (landscape), pongo boolean a true
         * y cargo el fragnment de la lista(izq) master*/

        if (findViewById(R.id.item_detail_container) != null) {

            mTwoPane = true;



            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.driver_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }


    @Override
    public void onItemSelected(String id) {

        if (mTwoPane) {


            // 4. AL HACER UN CLICK EN UN ELEMENTO DE LA LISTA, REEMPLAZO EL FRAGMENT DE LA DERECHA CON EL NUEVO CONTENIDO
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {

            // 5. SI NO HAY FRAGMENT DE LA DERECHA AL A HACER CLICK LLAMO A OTRA ACTIVITY
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivityForResult(detailIntent, 100);
        }
    }

    //13 CREAMOS UN METODO CERRAR
    @Override
    public void cerrar() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.item_detail_container);
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:

                break;
        }
    }
}
