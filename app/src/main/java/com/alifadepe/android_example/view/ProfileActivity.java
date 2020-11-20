package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.ProfileContract;
import com.alifadepe.android_example.databinding.ActivityProfileBinding;
import com.alifadepe.android_example.interactor.ProfileInteractor;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.model.User;
import com.alifadepe.android_example.presenter.ProfilePresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, View.OnClickListener {
    private ProfileContract.Presenter presenter;
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        setContentView(binding.getRoot());

        presenter = new ProfilePresenter(this, new ProfileInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setProfile();
        binding.baseLayout.pageTitle.setText("Profile");
        binding.signOutButton.setOnClickListener(this);
        binding.inputMotorButton.setOnClickListener(this);
        binding.baseLayout.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.signOutButton.getId()){
            onButtonSignOutClick();
        }
        if(v.getId() == binding.inputMotorButton.getId()){
            onButtonInputMotorClick();
        }
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
    }

    public void onButtonSignOutClick(){
        presenter.logout();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onButtonInputMotorClick(){
        Toast.makeText(this, "Input Motor", Toast.LENGTH_SHORT).show();
    }

    public void onBackButtonClick(){
        startActivity(new Intent(this, DashboardActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProfile(Profile user) {
        Toast.makeText(this, user.getFirst_name(), Toast.LENGTH_SHORT).show();
        binding.setUser(user);
    }
}
