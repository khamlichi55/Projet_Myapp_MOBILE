package com.example.myapp_ika;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class page11Activity extends AppCompatActivity {
        private RadioGroup rgAnswers;
        private Button btnNext;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page11);

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

                if (index == 0) { // 1 = bonne réponse "Paris"
                    score++;
                }

                Intent intent = new Intent(this, page2Activity.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
                finish();
            });
        }


}
