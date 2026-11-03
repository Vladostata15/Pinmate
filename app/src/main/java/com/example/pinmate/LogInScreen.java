package com.example.pinmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pinmate.databinding.ActivityLogInScreenBinding;

public class LogInScreen extends AppCompatActivity {

    private ActivityLogInScreenBinding binding;
    private String savedUsername;
    private String savedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLogInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            savedUsername = getIntent().getStringExtra("username");
            savedPassword = getIntent().getStringExtra("password");
        }

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInScreen.this, SignInScreen.class);
                startActivity(intent);
                finish();
            }
        });



        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etSignInUsername.getText().toString().trim();
                String password = binding.etSignInPassword.getText().toString().trim();

                if (savedUsername == null || savedPassword == null) {
                    Toast.makeText(LogInScreen.this, "No saved data", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.isEmpty()) {
                    Toast.makeText(LogInScreen.this, "Fill username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(LogInScreen.this, "Fill password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!username.equals(savedUsername) || !password.equals(savedPassword)) {
                    Toast.makeText(LogInScreen.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LogInScreen.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}