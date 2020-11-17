package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alifadepe.android_example.contract.RegisterContract;
import com.alifadepe.android_example.databinding.ActivityRegisterBinding;
import com.alifadepe.android_example.interactor.RegisterInteractor;
import com.alifadepe.android_example.model.User;
import com.alifadepe.android_example.presenter.RegisterPresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener {
    private RegisterContract.Presenter presenter;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.baseLayout.backButton.setEnabled(false);

        presenter = new RegisterPresenter(this, new RegisterInteractor());
        initView();
    }

    private void initView(){
        binding.registerButton.setOnClickListener(this);
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
    public void registerSuccess() {
        finish();
        Toast.makeText(this,"Register Success !", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void registerFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.registerButton.getId()){
            onButtonRegisterClick();
        }
    }

    public void onButtonRegisterClick(){
        User user = new User(binding.registerFirstName.getText().toString(),
                            binding.registerLastName.getText().toString(),
                            binding.registerUsername.getText().toString(),
                            binding.registerEmail.getText().toString(),
                            binding.registerPassword.getText().toString());
        presenter.register(user);
    }
}
