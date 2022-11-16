package br.gabriel.hachathon4periodo.datasources;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import br.gabriel.hachathon4periodo.Config;

public class BuscarDadosWeb extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {
        ArrayList<HashMap<String, String>> listaDados = new ArrayList<>();

        try {
            String link = strings[0];

            URL url = new URL(link);

            URLConnection connection = url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(inputStream);

            String dados = "";
            String linha;

            while ((linha = reader.readLine()) != null) {
                dados += linha;
            }

            JSONArray lista = new JSONArray(dados);

            for (int i = 0; i < lista.length(); i++) {

                JSONObject item = (JSONObject)lista.get(i);

                HashMap<String, String> mapaDados = new HashMap<>();
                mapaDados.put("nome", item.getString("nome"));
                mapaDados.put("sobre", item.getString("sobre"));
                mapaDados.put("experiencia", item.getString("experiencia"));
                mapaDados.put("salarioalvo", item.getString("salarioalvo"));
                mapaDados.put("prof_img", Config.linkImagem + item.getString("prof_img"));
                listaDados.add(mapaDados);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return listaDados;
    }
}
