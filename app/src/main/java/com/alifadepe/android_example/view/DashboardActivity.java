package com.alifadepe.android_example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.contract.LoginContract;
import com.alifadepe.android_example.databinding.ActivityDashboardBinding;
import com.alifadepe.android_example.interactor.DashboardInteractor;
import com.alifadepe.android_example.presenter.DashboardPresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View, View.OnClickListener {
    private DashboardContract.Presenter presenter;
    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DashboardPresenter(this, new DashboardInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        //binding.loginButton.setOnClickListener(this);
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
    public void onClick(View v) {
//        if(v.getId() == binding.loginButton.getId()){
//            onButtonLoginClick();
//        }
    }

//    public void onButtonLoginClick(){
//        presenter.login(binding.email.getText().toString(), binding.password.getText().toString());
//    }
}
