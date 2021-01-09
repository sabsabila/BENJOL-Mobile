package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.benjolteam.android_example.R;
import com.benjolteam.benjol.contract.MotorcycleContract;
import com.benjolteam.android_example.databinding.ActivityInputMotorcycleBinding;
import com.benjolteam.benjol.interactor.MotorcycleInteractor;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.presenter.MotorcyclePresenter;
import com.benjolteam.benjol.util.UtilProvider;

public class MotorcycleActivity extends AppCompatActivity implements MotorcycleContract.View, View.OnClickListener {
    private MotorcycleContract.presenter presenter;
    private ActivityInputMotorcycleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_motorcycle);
        setContentView(binding.getRoot());

        presenter = new MotorcyclePresenter(this, new MotorcycleInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.baseLayout.pageTitle.setText("Input Motorcycle");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.addMotorButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
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
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.addMotorButton.getId()){
            onAddButtonClick();
        }
        if(v.getId() == binding.navbar.homeButton.getId()){
            onHomeButtonClick();
        }
        if(v.getId() == binding.navbar.profileButton.getId()){
            onProfileClick();
        }
    }

    private void onHomeButtonClick() {
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void onProfileClick() {
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void onAddButtonClick() {
        Motorcycle motor = new Motorcycle(binding.motorBrand.getText().toString(),
                                        binding.motorPlateNumber.getText().toString());
        presenter.inputMotor(motor);
    }

    public void onBackButtonClick(){
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMotorSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
