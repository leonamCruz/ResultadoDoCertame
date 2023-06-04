package tech.leonam.resultadodocertame.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import tech.leonam.resultadodocertame.R;
import tech.leonam.resultadodocertame.controller.CriacaoDePdf;
import tech.leonam.resultadodocertame.model.entidade.ConfigProva;

public class CriarProva extends AppCompatActivity {
    private EditText qntAlternativas, qntDeQuestoes, nomeDaTurma, alternativasCorretas;
    private CheckBox isIndividual;
    private Button criarProva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_prova);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        iniciarComponentes();
        iniciarAnuncio();
        logicaIsChecked();
    }

    public void iniciarComponentes() {
        qntAlternativas = findViewById(R.id.txtQntDeAlternativas);
        qntDeQuestoes = findViewById(R.id.txtQntDeQuestao);
        nomeDaTurma = findViewById(R.id.txtTurma);
        alternativasCorretas = findViewById(R.id.txtQuestoesCorretas);
        isIndividual = findViewById(R.id.checkIndividual);
        criarProva = findViewById(R.id.botaoCriarProva);
    }

    public void iniciarAnuncio() {
        MobileAds.initialize(this, initializationStatus -> {});
        var request = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        interstitialAd.show(CriarProva.this);
                    }
                });
    }
    public void criarProva(){
        criarProva.setOnClickListener(e->{
            Toast.makeText(this, "Assista um anúncio enquanto criamos a prova.", Toast.LENGTH_SHORT).show();
            var thread = new Thread(this::iniciarAnuncio);
            thread.start();

            var configs = new ConfigProva();
            configs.setIndividual(isIndividual.isChecked());
            configs.setAlternativasCorretas(alternativasCorretas.getText().toString());
            configs.setQntDeQuestoes(qntDeQuestoes.getText().toString());
            configs.setQntAlternativas(qntAlternativas.getText().toString());
            configs.setNomeDaTurma(nomeDaTurma.getText().toString());

            new CriacaoDePdf(configs);
        });
    }
    public void logicaIsChecked() {
        isIndividual.setOnClickListener(e -> nomeDaTurma.setEnabled(isIndividual.isChecked()));
    }
}