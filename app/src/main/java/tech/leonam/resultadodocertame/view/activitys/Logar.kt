package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import tech.leonam.resultadodocertame.R

class Logar : AppCompatActivity() {
    private var logar: Button? = null
    private var recuperarSenha: Button? = null
    private var email: EditText? = null
    private var senha: EditText? = null
    private var cadastro: TextView? = null
    private var mAuth: FirebaseAuth? = null
    private val user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logar)
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        iniciarComponentes()
        logar()
        cadastrar()
        recuperarSenha()
    }

    fun iniciarComponentes() {
        logar = findViewById(R.id.botaoLogar)
        recuperarSenha = findViewById(R.id.botaoRecuperarSenha)
        email = findViewById(R.id.emailLogar)
        senha = findViewById(R.id.senhaLogar)
        cadastro = findViewById(R.id.cadastrar)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            abrirMenuPrincipal()
        }
    }

    fun logar() {
        logar!!.setOnClickListener { e: View? ->
            mAuth!!.signInWithEmailAndPassword(
                email!!.text.toString(), senha!!.text.toString()
            ).addOnCompleteListener(this) { task: Task<AuthResult?> ->
                val tarefa = if (task.isSuccessful) abrirMenuPrincipal() else abrirErroDeLogin()
            }
        }
    }

    fun abrirMenuPrincipal(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }

    fun abrirErroDeLogin(): Boolean {
        val dialog = AlertDialog.Builder(this@Logar)
        dialog.setTitle("Infelizmente não foi possível efetuar login")
        dialog.setMessage("Verifique seu Email ou Senha.")
        dialog.create().show()
        return true
    }

    fun cadastrar() {
        cadastro!!.setOnClickListener { e: View? ->
            startActivity(
                Intent(
                    this,
                    Cadastro::class.java
                )
            )
        }
    }

    fun recuperarSenha() {
        recuperarSenha!!.setOnClickListener { e: View? -> }
    }
}