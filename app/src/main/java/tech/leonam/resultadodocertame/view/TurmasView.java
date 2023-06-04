package tech.leonam.resultadodocertame.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
        setContentView(R.layout.activity_turmas_ealunos_view);
        iniciarComponentes();
        iniciarAds();
        clickBotao();
    }
    public void iniciarComponentes(){
        nomeTurma = findViewById(R.id.nomeTurmaCriarTurma);
        criarTurma = findViewById(R.id.botaoCriarTurma);
        ads = findViewById(R.id.adCriarTurma);
    }
    public void iniciarAds(){
        MobileAds.initialize(this, initializationStatus -> {});
        var request = new AdRequest.Builder().build();
        ads.loadAd(request);
    }
    public void clickBotao(){
        var nomeDaClasse = nomeTurma.getText().toString();
        var intencao = new Intent(this,AlunosView.class);
        intencao.putExtra("nomeDaClasse", nomeDaClasse);
        startActivity(intencao);
    }
}