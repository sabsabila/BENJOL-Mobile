package com.alifadepe.android_example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alifadepe.android_example.contract.LoginContract;
import com.alifadepe.android_example.databinding.ActivityLoginBinding;
import com.alifadepe.android_example.interactor.LoginInteractor;
import com.alifadepe.android_example.presenter.LoginPresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.loginButton.setOnClickListener(this);
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
        finish();
        startActivity(new Intent(this, ListBookActivity.class));
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.loginButton.getId()){
            onButtonLoginClick();
        }
    }

    public void onButtonLoginClick(){
        presenter.login(binding.username.getText().toString(), binding.password.getText().toString());
    }
}
