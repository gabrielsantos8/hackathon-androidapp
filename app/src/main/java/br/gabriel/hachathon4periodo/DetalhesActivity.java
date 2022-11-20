package br.gabriel.hachathon4periodo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.gabriel.hachathon4periodo.datasources.DownloadImagem;

public class DetalhesActivity extends AppCompatActivity {

    TextView nome;
    TextView experiencia;
    TextView sobre;
    TextView salarioalvo;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        nome =  findViewById(R.id.nome);
        experiencia =  findViewById(R.id.experiencia);
        sobre =  findViewById(R.id.sobre);
        salarioalvo =  findViewById(R.id.salarioalvo);
        imagem = findViewById(R.id.imagem);

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos != null) {

            Bundle params = dadosRecebidos.getExtras();

            if (params != null) {

                nome.setText(params.getString("nome"));
                experiencia.setText("Experiência: " + params.getString("experiencia") + " anos.");
                sobre.setText("Sobre: " + params.getString("sobre") + ".");
                salarioalvo.setText("Salário Alvo: R$ " + params.getString("salarioalvo") + ".");
                new DownloadImagem(imagem).execute(params.getString("prof_img"));
            }

        }
    }

}