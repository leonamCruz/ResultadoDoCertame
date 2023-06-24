package tech.leonam.resultadodocertame.view.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tech.leonam.resultadodocertame.R;

public class TurmasView extends AppCompatActivity {
    private EditText nomeTurma;
    private Button criarTurma;
    private AdView ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        setContentView(R.layout.turmas);
        iniciarComponentes();
        //Habilitar somente quando for para produção
        //TODO iniciarAds();
        clickBotao();
    }

    public void iniciarComponentes() {
        nomeTurma = findViewById(R.id.nomeTurmaCriarTurma);
        criarTurma = findViewById(R.id.botaoCriarTurma);
        ads = findViewById(R.id.adCriarTurma);
    }

    public void iniciarAds() {
        MobileAds.initialize(this, initializationStatus -> {
        });
        var request = new AdRequest.Builder().build();
        ads.loadAd(request);
    }

    public void clickBotao() {
        criarTurma.setOnClickListener(e -> {
            var nomeDaClasse = nomeTurma.getText().toString();
            if (nomeDaClasse == null || nomeDaClasse.isBlank()) {
                Toast.makeText(this, getString(R.string.insira_o_nome_da_turma), Toast.LENGTH_SHORT).show();
            } else {
                var intencao = new Intent(this, AlunosView.class);
                intencao.putExtra("nomeDaClasse", nomeDaClasse);
                startActivity(intencao);
            }
        });
    }
}