package com.example.pinmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pinmate.databinding.ActivitySignInScreenBinding;

public class SignInScreen extends AppCompatActivity {

    private ActivitySignInScreenBinding binding;
    private SharedPreferences prefs;

    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember_me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        if (prefs.getBoolean(KEY_REMEMBER, false)) {
            binding.etSignInUsername.setText(prefs.getString(KEY_USERNAME, ""));
            binding.etSignInPassword.setText(prefs.getString(KEY_PASSWORD, ""));
            binding.cbRememberMe.setChecked(true);
        }

        if (getIntent() != null) {
            String savedUsername = getIntent().getStringExtra("username");
            String savedPassword = getIntent().getStringExtra("password");
            if (savedUsername != null) {
                binding.etSignInUsername.setText(savedUsername);
            }
            if (savedPassword != null) {
                binding.etSignInPassword.setText(savedPassword);
            }
        }

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInScreen.this, SignUpScreen.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etSignInUsername.getText().toString().trim();
                String password = binding.etSignInPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    binding.etSignInUsername.setError("Fill username");
                    binding.etSignInUsername.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    binding.etSignInPassword.setError("Fill password");
                    binding.etSignInPassword.requestFocus();
                    return;
                }

                if (binding.cbRememberMe.isChecked()) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(KEY_USERNAME, username);
                    editor.putString(KEY_PASSWORD, password);
                    editor.putBoolean(KEY_REMEMBER, true);
                    editor.apply();
                }
            }
        });
    }
}