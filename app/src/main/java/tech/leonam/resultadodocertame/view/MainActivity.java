package tech.leonam.resultadodocertame.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tech.leonam.resultadodocertame.R;

public class MainActivity extends AppCompatActivity {
    private ImageView turmaView,alunoView,criarPessoaETurmaView,criarProvaView,sobreView,escanearView;
    private AdView ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        iniciarAnuncio();
    }
    public void iniciarComponentes(){
        turmaView = findViewById(R.id.turmaMain);
        alunoView = findViewById(R.id.alunoMain);
        criarProvaView = findViewById(R.id.criarProvaMain);
        escanearView = findViewById(R.id.escanearMain);
        sobreView = findViewById(R.id.sobreMain);
        criarPessoaETurmaView = findViewById(R.id.criarPessoasETurmasMain);
        ads = findViewById(R.id.adPrincipal);
    }
    public void iniciarAnuncio(){
        MobileAds.initialize(this, initializationStatus -> {});
        var pedeAnuncio = new AdRequest.Builder().build();
        ads.loadAd(pedeAnuncio);
    }
}