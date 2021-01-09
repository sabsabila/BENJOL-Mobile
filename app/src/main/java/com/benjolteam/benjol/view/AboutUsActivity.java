package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.benjolteam.android_example.databinding.ActivityAboutUsBinding;
import com.benjolteam.benjol.contract.AboutUsContract;
import com.benjolteam.benjol.presenter.AboutUsPresenter;

public class AboutUsActivity extends AppCompatActivity implements AboutUsContract.View, View.OnClickListener {
    private AboutUsContract.Presenter presenter;
    private ActivityAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new AboutUsPresenter(this);
        initView();
    }

    private void initView(){
        binding.pageTitle.setText("About Us");
        binding.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.backButton.getId()){
            onBackClick();
        }
    }

    private void onBackClick() {
        presenter.redirectToSplash();
    }

    @Override
    public void backToSplash() {
        startActivity(new Intent(this, SplashActivity.class));
    }
}
