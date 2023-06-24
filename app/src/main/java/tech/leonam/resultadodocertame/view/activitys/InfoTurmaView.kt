package tech.leonam.resultadodocertame.view.activitys

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tech.leonam.resultadodocertame.databinding.ActivityInfoTurmaViewBinding
import tech.leonam.resultadodocertame.modelView.service.PegaTurmasService
import tech.leonam.resultadodocertame.view.adapters.ReciclerViewAdapter

class InfoTurmaView : AppCompatActivity() {
    private lateinit var binding: ActivityInfoTurmaViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoTurmaViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        iniciarReciclagem()
    }

    private fun iniciarReciclagem() {
        val list = PegaTurmasService().getTurmas(this)
        val adapter = ReciclerViewAdapter(this,list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.reciclavel.setHasFixedSize(true)
        binding.reciclavel.layoutManager = layoutManager
        binding.reciclavel.adapter = adapter
    }
}