package com.example.pinmate;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pinmate.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_with_move_down);
        binding.logo.startAnimation(logoAnimation);

        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_with_move_up);
        binding.title.startAnimation(textAnimation);
    }
}