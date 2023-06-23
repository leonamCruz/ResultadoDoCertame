package tech.leonam.resultadodocertame.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tech.leonam.resultadodocertame.R;
import tech.leonam.resultadodocertame.modelView.PegaTurmas;
import tech.leonam.resultadodocertame.view.adapters.ReciclerViewAdapter;

public class InfoTurmaView extends AppCompatActivity {
    private ReciclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_turma_view);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.BLACK);
        iniciarReciclagem();
    }

    public void iniciarReciclagem() {
        RecyclerView recyclerView = findViewById(R.id.reciclavel);
        adapter = new ReciclerViewAdapter(this, new PegaTurmas().getTurmas(this));
        var layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}