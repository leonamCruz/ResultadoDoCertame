package tech.leonam.resultadodocertame.view;

import android.app.AlertDialog;
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
import tech.leonam.resultadodocertame.modelView.ConfigProvaService;
import tech.leonam.resultadodocertame.modelView.CriacaoDePdf;
import tech.leonam.resultadodocertame.modelView.ServiceSalvaProvas;

public class CriarProva extends AppCompatActivity {
    private EditText qntAlternativas, qntDeQuestoes, nomeDaTurma, alternativasCorretas, identificacaoProva;
    private CheckBox isIndividual;
    private Button criarProva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, initializationStatus -> {
        });
        setContentView(R.layout.activity_criar_prova);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        iniciarComponentes();
        //HABILITAR SOMENTE QUANDO FOR PARA PRODUCAO
        // TODO iniciarAnuncio(new AdRequest.Builder().build());
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
        } catch (Exception ignored) {
        }
    }

    public void criarProva() {
        criarProva.setOnClickListener(e -> {
            var qntdDeCorretasDigitadas = alternativasCorretas.getText().toString().split("\n").length;
            if (Integer.parseInt(qntAlternativas.getText().toString()) != qntdDeCorretasDigitadas) {
                var dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Erro");
                dialog.setMessage("A quantidade de alternativas está diferente da quantidade de alternativas corretas que você forneceu");
                dialog.setCancelable(true);
                dialog.create().show();
            } else {

                var configs = new ConfigProvaService();
                configs.setIndividual(isIndividual.isChecked());
                configs.setAlternativasCorretas(alternativasCorretas.getText().toString());
                configs.setQntDeQuestoes(qntDeQuestoes.getText().toString());
                configs.setQntAlternativas(qntAlternativas.getText().toString());
                configs.setNomeDaTurma(nomeDaTurma.getText().toString());
                configs.setIdentificacaoProva(identificacaoProva.getText().toString());
                System.out.println(configs.getIdentificacaoProva());

                try {
                    new CriacaoDePdf(configs).criaPdf(this);
                    new ServiceSalvaProvas().salvaProvas(this, configs);
                    Toast.makeText(this, "Prova Criada com sucesso", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(this, "Deu Muita MERDA!!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}