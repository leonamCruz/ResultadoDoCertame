package tech.leonam.resultadodocertame.view;
// Hello World!!!!!
// Legal
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

import java.util.ArrayList;

import tech.leonam.resultadodocertame.R;
import tech.leonam.resultadodocertame.controller.ControleDeTurma;
import tech.leonam.resultadodocertame.model.entidade.AlunoEntidade;
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public class AlunosView extends AppCompatActivity {
    private String nomeDaClasse;
    private EditText nomesAlunos;
    private Button criarAlunos;
    private AdView ads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos_view);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.BLACK);
        nomeDaClasse = getIntent().getStringExtra("nomeDaClasse");
        iniciarComponentes();
        //TODO iniciar somente quando for para produção
        //iniciarAds();
        setarHint();
        clickBotao();
    }
    public void iniciarComponentes(){
        nomesAlunos = findViewById(R.id.alunosEditText);
        criarAlunos = findViewById(R.id.adicionarAlunos);
        ads = findViewById(R.id.adView);
    }
    public void iniciarAds(){
        MobileAds.initialize(this, initializationStatus -> {});
            var request = new AdRequest.Builder().build();
            if(request!=null){
                ads.loadAd(request);
            }
    }
    public void clickBotao() {
        criarAlunos.setOnClickListener(e -> {
            var nomeAlunos = nomesAlunos.getText().toString().trim().replaceAll("[\\r\\n]", "");
            var alunos = nomeAlunos.split(";");

            var lista = new ArrayList<AlunoEntidade>();

            for (var aluno : alunos) {
                var entidadeAluno = new AlunoEntidade();
                entidadeAluno.setNome(aluno);
                lista.add(entidadeAluno);
            }
            var turmaEntidade = new TurmaEntidade(lista, nomeDaClasse);

            if (ControleDeTurma.cadastre(turmaEntidade, this)) {
                var intencao = new Intent(this, MainActivity.class);
                Toast.makeText(this, R.string.classe_criada_com_sucesso, Toast.LENGTH_SHORT).show();
                intencao.putExtra("nomeDaClasse", nomeDaClasse);
                startActivity(intencao);
            }
            Toast.makeText(this, R.string.infelizmente_a_classe_n_o_foi_criada, Toast.LENGTH_SHORT).show();

        });
    }
    public void setarHint(){
        nomesAlunos.setHint("A turma " + nomeDaClasse + " está sendo criada." +
                "Por favor, separe o nome dos alunos com ;");
    }
}