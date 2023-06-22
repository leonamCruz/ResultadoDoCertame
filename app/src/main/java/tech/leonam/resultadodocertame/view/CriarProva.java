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

import java.io.IOException;

import tech.leonam.resultadodocertame.R;
import tech.leonam.resultadodocertame.service.CriacaoDePdf;
import tech.leonam.resultadodocertame.model.entidade.ConfigProva;
import tech.leonam.resultadodocertame.service.ServiceSalvaProvas;

public class CriarProva extends AppCompatActivity {
    private EditText qntAlternativas, qntDeQuestoes, nomeDaTurma, alternativasCorretas,identificacaoProva;
    private CheckBox isIndividual;
    private Button criarProva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, initializationStatus -> {});
        setContentView(R.layout.activity_criar_prova);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        iniciarComponentes();
        //HABILITAR SOMENTE QUANDO FOR PARA PRODUCAO
        // TODO iniciarAnuncio(new AdRequest.Builder().build());
        logicaIsChecked();
        criarProva();
    }

    public void iniciarComponentes() {
        qntAlternativas = findViewById(R.id.txtQntDeAlternativas);
        qntDeQuestoes = findViewById(R.id.txtQntDeQuestao);
        nomeDaTurma = findViewById(R.id.txtTurma);
        alternativasCorretas = findViewById(R.id.txtQuestoesCorretas);
        isIndividual = findViewById(R.id.checkIndividual);
        criarProva = findViewById(R.id.botaoCriarProva);
        identificacaoProva = findViewById(R.id.identificacaoCriarProva);
    }

    public void iniciarAnuncio(AdRequest request) {
        try {
            InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", request,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            interstitialAd.show(CriarProva.this);
                        }
                    });
        } catch (Exception ignored) {}
    }
    public void criarProva(){
        criarProva.setOnClickListener(e->{
            Toast.makeText(this, "Assista um anúncio enquanto criamos a prova.", Toast.LENGTH_SHORT).show();
            var thread = new Thread(()->iniciarAnuncio(new AdRequest.Builder().build()));
            thread.start();

            var criarProvaThread = new Thread(()->{
                var configs = new ConfigProva();
                configs.setIndividual(isIndividual.isChecked());
                configs.setAlternativasCorretas(alternativasCorretas.getText().toString());
                configs.setQntDeQuestoes(qntDeQuestoes.getText().toString());
                configs.setQntAlternativas(qntAlternativas.getText().toString());
                configs.setNomeDaTurma(nomeDaTurma.getText().toString());
                configs.setIdentificacaoProva(identificacaoProva.getText().toString());

                try {
                    new CriacaoDePdf(configs).criaPdf(this);
                    new ServiceSalvaProvas().salvaProvas(this,configs);
                } catch (IOException ex) {
                    Toast.makeText(this, "Deu Muita MERDA!!!!!", Toast.LENGTH_SHORT).show();
                }
            });
            criarProvaThread.start();
        });
    }
    public void logicaIsChecked() {
        isIndividual.setOnClickListener(e -> nomeDaTurma.setEnabled(isIndividual.isChecked()));
    }
}