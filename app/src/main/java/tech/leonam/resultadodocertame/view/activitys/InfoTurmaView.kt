package tech.leonam.resultadodocertame.view.activitys

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.modelView.service.PegaTurmasService
import tech.leonam.resultadodocertame.view.adapters.ReciclerViewAdapter

class InfoTurmaView : AppCompatActivity() {
    private var adapter: ReciclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_turma_view)
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        iniciarReciclagem()
    }

    fun iniciarReciclagem() {
        val recyclerView = findViewById<RecyclerView>(R.id.reciclavel)
        adapter = ReciclerViewAdapter(this, PegaTurmasService().getTurmas(this))
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}