package tech.leonam.resultadodocertame.view.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tech.leonam.resultadodocertame.R;

public class MainActivity extends AppCompatActivity {
    private ImageView turmaView, criarPessoaETurmaView, criarProvaView, sobreView, escanearView;
    private AdView ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        iniciarComponentes();
        //TODO INICIAR SOMENTE QUANDO FOR PARA PRODUÇÃO
        //iniciarAnuncio();
        clickCriar();
        clickCriarProva();
        clickTurma();
    }

    public void iniciarComponentes() {
        turmaView = findViewById(R.id.turmaMain);
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

    public void clickCriarProva() {
        criarProvaView.setOnClickListener(e -> startActivity(new Intent(this, CriarProva.class)));
    }

    public void clickTurma() {
        turmaView.setOnClickListener(e-> startActivity(new Intent(this, InfoTurmaView.class)));
    }
}