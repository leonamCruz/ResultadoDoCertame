package tech.leonam.resultadodocertame.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tech.leonam.resultadodocertame.R;

public class Logar extends AppCompatActivity {
    private Button logar, recuperarSenha;
    private EditText email, senha;
    private TextView cadastro;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);
        iniciarComponentes();
        firebaseInit();
        logar();
        cadastrar();
        recuperarSenha();
    }

    public void iniciarComponentes() {
        logar = findViewById(R.id.botaoLogar);
        recuperarSenha = findViewById(R.id.botaoRecuperarSenha);
        email = findViewById(R.id.emailLogar);
        senha = findViewById(R.id.senhaLogar);
        cadastro = findViewById(R.id.cadastrar);
    }

    public void firebaseInit() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        var currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //Iniciar Aplicativo TODO
        }
    }

    public void logar() {
        logar.setOnClickListener(e -> mAuth.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).addOnCompleteListener(this, task -> {
            boolean tarefa = task.isSuccessful() ? abrirMenuPrincipal() : abrirErroDeLogin();
        }));
    }

    public boolean abrirMenuPrincipal() {
        var intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    public boolean abrirErroDeLogin() {
        var dialog = new AlertDialog.Builder(Logar.this);
        dialog.setTitle("Infelizmente não foi possível efetuar login");
        dialog.setMessage("Verifique seu Email ou Senha.");
        dialog.create().show();
        return true;
    }

    public void cadastrar() {
        //TODO Abrir outra tela
    }

    public void recuperarSenha() {
        recuperarSenha.setOnClickListener(e -> {
            //var intent = new Intent();
            //TODO janela nova pra abrir
        });
    }
}