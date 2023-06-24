package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.databinding.ActivityCadastroBinding

class Cadastro : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        setContentView(R.layout.activity_cadastro)
        cadastrar()
    }

    private fun cadastrar() {
        binding.botaoCadastro.setOnClickListener {
            mAuth!!.createUserWithEmailAndPassword(
                binding.emailCadastro.text.toString(), binding.senhaCadastro.text.toString()
            ).addOnCompleteListener(this) { task: Task<AuthResult?> ->
                if (task.isSuccessful)
                    startActivity(Intent(this, Logar::class.java))
                else Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}