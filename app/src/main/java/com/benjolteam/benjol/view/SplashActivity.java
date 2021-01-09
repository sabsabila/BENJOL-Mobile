package com.benjolteam.benjol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.benjolteam.android_example.databinding.SplashBinding;
import com.benjolteam.benjol.contract.SplashContract;
import com.benjolteam.benjol.presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements SplashContract.View, View.OnClickListener {
    private SplashContract.Presenter presenter;
    private SplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SplashPresenter(this);
        initView();
    }

    private void initView(){
        binding.splashScreen.setOnClickListener(this);
        binding.aboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.aboutUs.getId()){
           onAboutClick();
        }
        if(v.getId() == binding.splashScreen.getId()){
            onSplashClick();
        }
    }

    private void onSplashClick() {
        presenter.redirectToApp();
    }

    private void onAboutClick() {
        presenter.redirectToAboutUs();
    }

    @Override
    public void startApp() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showAboutUs() {
        finish();
        startActivity(new Intent(this, AboutUsActivity.class));
    }
}
