package tech.leonam.resultadodocertame.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tech.leonam.resultadodocertame.R;
import tech.leonam.resultadodocertame.controller.CriacaoDePdf;
import tech.leonam.resultadodocertame.model.entidade.ConfigProva;

public class MainActivity extends AppCompatActivity {
    private ImageView turmaView, alunoView, criarPessoaETurmaView, criarProvaView, sobreView, escanearView;
    private AdView ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        iniciarComponentes();
        iniciarAnuncio();
        clickCriar();
        clickCriarProva();





        var alert = new AlertDialog.Builder(this);
        alert.setMessage("Clique em sim");
        alert.setPositiveButton("Sim",((dialog, which) -> {
            try {
                var config = new ConfigProva();
                config.setQntDeQuestoes("50");
                config.setQntAlternativas("2");

                new CriacaoDePdf(config).criaPdf(this);
            }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }));
        alert.create().show();
    }
    public void iniciarComponentes() {
        turmaView = findViewById(R.id.turmaMain);
        alunoView = findViewById(R.id.alunoMain);
        criarProvaView = findViewById(R.id.criarProvaMain);
        escanearView = findViewById(R.id.escanearMain);
        sobreView = findViewById(R.id.sobreMain);
        criarPessoaETurmaView = findViewById(R.id.criarPessoasETurmasMain);
        ads = findViewById(R.id.adPrincipal);
    }

    public void iniciarAnuncio() {
        MobileAds.initialize(this, initializationStatus -> {
        });
        var pedeAnuncio = new AdRequest.Builder().build();
        ads.loadAd(pedeAnuncio);
    }

    public void clickCriar() {
        criarPessoaETurmaView.setOnClickListener(e -> startActivity(new Intent(this, TurmasView.class)));
    }
    public void clickCriarProva(){
        criarProvaView.setOnClickListener(e-> startActivity(new Intent(this, CriarProva.class)));
    }
}