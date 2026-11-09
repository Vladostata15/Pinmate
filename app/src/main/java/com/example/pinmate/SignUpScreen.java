package com.example.pinmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pinmate.databinding.ActivitySignUpScreenBinding;

public class SignUpScreen extends AppCompatActivity {

    private ActivitySignUpScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignUpScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUsername.getText().toString().trim();
                String password = binding.etSignUpPassword.getText().toString().trim();
                String confirmPassword = binding.etConfirmPassword.getText().toString().trim();
                int age = binding.numberPickerAge.getValue();
                int selectedGenderId = binding.rgGender.getCheckedRadioButtonId();

                if (username.isEmpty()) {
                    binding.etUsername.setError("Fill Username");
                    binding.etUsername.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    binding.etSignUpPassword.setError("Password must be at least 6 characters long");
                    binding.etSignUpPassword.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    binding.etConfirmPassword.setError("Passwords don't match");
                    binding.etConfirmPassword.requestFocus();
                    return;
                }

                if (selectedGenderId == -1) {
                    Toast.makeText(SignUpScreen.this, "Select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender.getText().toString();

                Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("age", age);
                intent.putExtra("gender", gender);
                startActivity(intent);
                finish();
            }
        });
    }
}