package br.gabriel.hachathon4periodo;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
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
                new String[] { "nome" },
                new int[] { R.id.txtNome }
        );
        setListAdapter(adaptador);
    }
}