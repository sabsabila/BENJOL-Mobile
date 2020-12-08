package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.MotorcycleContract;
import com.alifadepe.android_example.databinding.ActivityInputMotorcycleBinding;
import com.alifadepe.android_example.interactor.MotorcycleInteractor;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.presenter.MotorcyclePresenter;
import com.alifadepe.android_example.util.UtilProvider;

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
        binding.baseLayout.pageTitle.setText("Motorcycle");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.addMotorButton.setOnClickListener(this);
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
