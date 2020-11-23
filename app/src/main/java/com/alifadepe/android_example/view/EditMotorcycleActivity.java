package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.EditMotorcycleContract;
import com.alifadepe.android_example.contract.MotorcycleContract;
import com.alifadepe.android_example.databinding.ActivityInputMotorcycleBinding;
import com.alifadepe.android_example.interactor.EditMotorcycleInteractor;
import com.alifadepe.android_example.interactor.MotorcycleInteractor;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.presenter.EditMotorcyclePresenter;
import com.alifadepe.android_example.presenter.MotorcyclePresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class EditMotorcycleActivity extends AppCompatActivity implements EditMotorcycleContract.View, View.OnClickListener {
    private EditMotorcycleContract.presenter presenter;
    private ActivityInputMotorcycleBinding binding;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_motorcycle);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        presenter = new EditMotorcyclePresenter(this, new EditMotorcycleInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setMotor(id);
        binding.baseLayout.pageTitle.setText("Edit Motorcycle");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.addMotorButton.setText("Save");
        binding.addMotorButton.setOnClickListener(this);
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
        presenter.editMotor(id, motor);
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
    public void setMotor(Motorcycle motorcycle) {
        binding.motorBrand.setText(motorcycle.getBrand());
        binding.motorPlateNumber.setText(motorcycle.getPlate_number());
    }

    @Override
    public void editMotorSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
