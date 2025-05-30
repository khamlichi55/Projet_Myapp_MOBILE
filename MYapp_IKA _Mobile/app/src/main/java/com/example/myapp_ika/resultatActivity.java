package com.example.myapp_ika;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultatActivity extends AppCompatActivity {

    TextView tvScore, tvMessage;
    Button btnRetry, btnExit;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        tvScore = findViewById(R.id.tv_final_score);
        tvMessage = findViewById(R.id.tv_message);
        progressBar = findViewById(R.id.progressBar);
        btnRetry = findViewById(R.id.btn_retry);
        btnExit = findViewById(R.id.btn_exit);

        // Récupération du score depuis l'intent
        int score = getIntent().getIntExtra("SCORE_FINAL", 0);
        int total = getIntent().getIntExtra("TOTAL_QUESTIONS", 1); // default 1 pour éviter division par zéro

        // Afficher le score
        tvScore.setText("Votre score : " + score + " / " + total);

        // Mise à jour de la ProgressBar et du TextView en fonction du score
        progressBar.setProgress(100 * score / total); // Supposant un total de 8 questions
        tvScore.setText(100 * score / total + " %");  // Affichage du pourcentage

        // Message en fonction du score
        if (score == total) {
            tvMessage.setText("Excellent ! 🎯");
        } else if (score > total / 2) {
            tvMessage.setText("Bien joué !");
        } else {
            tvMessage.setText("Tu peux faire mieux 😊");
        }

        // Bouton Rejouer
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retryIntent = new Intent(resultatActivity.this, page11Activity.class);
                startActivity(retryIntent);
                finish(); // pour ne pas revenir sur cette activité en appuyant sur back
            }
        });

        // Bouton Quitter
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // ferme toutes les activités de l'application
            }
        });
    }
}
