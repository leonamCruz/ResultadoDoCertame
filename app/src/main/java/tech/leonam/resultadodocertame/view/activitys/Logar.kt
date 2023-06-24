package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import tech.leonam.resultadodocertame.databinding.ActivityLogarBinding

class Logar : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityLogarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        logar()
        cadastrar()
        recuperarSenha()
    }

    override fun onStart() {
        super.onStart()
        if (mAuth?.currentUser != null) abrirMenuPrincipal()
    }

    private fun logar() {
        binding.botaoLogar.setOnClickListener {
            mAuth!!.signInWithEmailAndPassword(
                binding.emailLogar.text.toString(), binding.senhaLogar.text.toString()
            ).addOnCompleteListener(this) { task: Task<AuthResult?> ->
                if (task.isSuccessful) abrirMenuPrincipal() else abrirErroDeLogin()
            }
        }
    }

    private fun abrirMenuPrincipal(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }

    private fun abrirErroDeLogin(): Boolean {
        val dialog = AlertDialog.Builder(this@Logar)
        dialog.setTitle("Infelizmente não foi possível efetuar login")
        dialog.setMessage("Verifique seu Email ou Senha.")
        dialog.create().show()
        return true
    }

    private fun cadastrar() {
        binding.cadastrar.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Cadastro::class.java
                )
            )
        }
    }

    private fun recuperarSenha() {
        binding.botaoRecuperarSenha.setOnClickListener{}
    }
}