package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.benjolteam.benjol.contract.PickupContract;
import com.benjolteam.android_example.databinding.ActivityPickupBinding;
import com.benjolteam.benjol.presenter.PickupPresenter;

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
        if(binding.pickupLocation.getText().toString().isEmpty() || binding.deliveryLocation.getText().toString().isEmpty())
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        else{
            ArrayList<String> location = new ArrayList<String>();
            location.add(binding.pickupLocation.getText().toString());
            location.add(binding.deliveryLocation.getText().toString());

            presenter.inputLocation(location);
        }
    }
}
