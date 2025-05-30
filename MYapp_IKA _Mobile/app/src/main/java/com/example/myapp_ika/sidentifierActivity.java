package com.example.myapp_ika;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class sidentifierActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button bRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  // Assure-toi que ce fichier XML existe

        // Initialisation Firebase
        mAuth = FirebaseAuth.getInstance();

        // Initialisation des vues
        etEmail = findViewById(R.id.etemail);      // à créer dans le XML
        etPassword = findViewById(R.id.etpassword); // à créer dans le XML
        bRegister = findViewById(R.id.bregister);   // à créer dans le XML

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(sidentifierActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Création de l'utilisateur Firebase
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(sidentifierActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(sidentifierActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(sidentifierActivity.this, loginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(sidentifierActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
