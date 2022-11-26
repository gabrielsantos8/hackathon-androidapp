package br.gabriel.hachathon4periodo;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import br.gabriel.hachathon4periodo.datasources.BuscarDadosWeb;

public class MainActivity extends ListActivity {

    private ArrayList<HashMap<String,String>> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            dados = new BuscarDadosWeb().execute(Config.urlApi).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String nome = dados.get(0).get("nome");
        Toast.makeText(this, nome, Toast.LENGTH_LONG).show();


        ListAdapter adaptador = new SimpleAdapter(
                this,
                dados,
                R.layout.listview_modelo,
                new String[] { "nome"},
                new int[] { R.id.txtNome}
        );
        setListAdapter(adaptador);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        HashMap<String, String> rec = dados.get(position);

        Intent telaDetalhes = new Intent(MainActivity.this, DetalhesActivity.class);

        Bundle parms = new Bundle();
        parms.putString("nome", rec.get("nome"));
        parms.putString("prof_img", rec.get("prof_img"));
        parms.putString("experiencia", rec.get("experiencia"));
        parms.putString("sobre", rec.get("sobre"));
        parms.putString("salarioalvo", rec.get("salarioalvo"));

        telaDetalhes.putExtras(parms);

        startActivity(telaDetalhes);
    }



}