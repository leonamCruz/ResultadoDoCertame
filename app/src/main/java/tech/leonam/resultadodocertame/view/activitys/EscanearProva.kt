package tech.leonam.resultadodocertame.view.activitys

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.resultadodocertame.databinding.ActivityEscanearProvaBinding

class EscanearProva : AppCompatActivity() {
    private lateinit var binding: ActivityEscanearProvaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
    }
}