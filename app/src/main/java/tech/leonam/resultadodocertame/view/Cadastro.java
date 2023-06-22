package tech.leonam.resultadodocertame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tech.leonam.resultadodocertame.R;

public class Cadastro extends AppCompatActivity {
    private Button cadastro;
    private EditText email, senha;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_cadastro);
        iniciarComponentes();
        cadastrar();
    }

    public void iniciarComponentes() {
        cadastro = findViewById(R.id.botaoCadastro);
        email = findViewById(R.id.emailCadastro);
        senha = findViewById(R.id.senhaCadastro);
        mAuth = FirebaseAuth.getInstance();
    }

    public void cadastrar() {
        cadastro.setOnClickListener(e -> mAuth.createUserWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(senha.getText())).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(this, Logar.class));
            } else {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));
    }
}