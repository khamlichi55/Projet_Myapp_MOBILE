package com.example.myapp_ika;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class page5Activity  extends AppCompatActivity {
    private RadioGroup rgAnswers;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);

        rgAnswers = findViewById(R.id.rgAnswers);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            int score = getIntent().getIntExtra("SCORE", 0);
            int selectedId = rgAnswers.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Choisissez une réponse", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            int index = rgAnswers.indexOfChild(selectedRadio);

            if (index == 2) {
                score++;
            }

            Intent resultIntent = new Intent(page5Activity.this, resultatActivity.class);
            resultIntent.putExtra("SCORE_FINAL", score); // score = variable que tu as mis à jour
            resultIntent.putExtra("TOTAL_QUESTIONS", 5);
            startActivity(resultIntent);
            finish();
        });
    }


}
