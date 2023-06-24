package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import tech.leonam.resultadodocertame.R

class Cadastro : AppCompatActivity() {
    private var cadastro: Button? = null
    private var email: EditText? = null
    private var senha: EditText? = null
    private var mAuth: FirebaseAuth? = null
    private val user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        setContentView(R.layout.activity_cadastro)
        iniciarComponentes()
        cadastrar()
    }

    fun iniciarComponentes() {
        cadastro = findViewById(R.id.botaoCadastro)
        email = findViewById(R.id.emailCadastro)
        senha = findViewById(R.id.senhaCadastro)
        mAuth = FirebaseAuth.getInstance()
    }

    fun cadastrar() {
        cadastro!!.setOnClickListener { e: View? ->
            mAuth!!.createUserWithEmailAndPassword(
                email!!.text.toString(), senha!!.text.toString()
            ).addOnCompleteListener(this) { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, Logar::class.java))
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}