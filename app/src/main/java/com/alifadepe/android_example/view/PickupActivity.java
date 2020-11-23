package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alifadepe.android_example.contract.PickupContract;
import com.alifadepe.android_example.databinding.ActivityPickupBinding;
import com.alifadepe.android_example.model.Booking;
import com.alifadepe.android_example.presenter.PickupPresenter;

import java.util.ArrayList;

public class PickupActivity extends AppCompatActivity implements PickupContract.View, View.OnClickListener {
    private PickupContract.Presenter presenter;
    private ActivityPickupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPickupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.baseLayout.backButton.setEnabled(true);

        presenter = new PickupPresenter(this);
        initView();
    }

    private void initView(){
        binding.baseLayout.pageTitle.setText("Pick Up");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.confirmButton.setOnClickListener(this);
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
    public void sendLocation(ArrayList<String> location) {
        Intent returnIntent = new Intent();
        returnIntent.putStringArrayListExtra("location", location);
        setResult(RESULT_OK, returnIntent);

        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.confirmButton.getId())
            onButtonPickupClick();
        if(v.getId() == binding.baseLayout.backButton.getId())
            onBackPressed();
    }

    public void onButtonPickupClick(){
        ArrayList<String> location = new ArrayList<String>();
        location.add(binding.pickupLocation.getText().toString());
        location.add(binding.deliveryLocation.getText().toString());

        presenter.inputLocation(location);
    }
}
