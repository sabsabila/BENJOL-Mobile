package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.ProgressPickupContract;
import com.alifadepe.android_example.databinding.ActivityProgressPickupBinding;
import com.alifadepe.android_example.interactor.ProgressPickupInteractor;
import com.alifadepe.android_example.model.Pickup;
import com.alifadepe.android_example.presenter.ProgressPickupPresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class ProgressPickupActivity extends AppCompatActivity implements ProgressPickupContract.View, View.OnClickListener {
    private ProgressPickupContract.Presenter presenter;
    private ActivityProgressPickupBinding binding;
    private  int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressPickupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bookingId = intent.getIntExtra("booking_id", 0);

        presenter = new ProgressPickupPresenter(this, new ProgressPickupInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setProgressService(bookingId);
        binding.baseLayout.pageTitle.setText("Progress Service");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
        binding.baseLayout.backButton.setOnClickListener(this);
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

    private void onBackButtonClick() {
        finish();
        startActivity(new Intent(this, ListPickupActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressPickup(Pickup pickup) {
        if(pickup != null){
            binding.setPickup(pickup);

            if(pickup.getStatus().equalsIgnoreCase("picking up"))
                binding.pickupStatusPickingUp.setBackgroundResource(R.drawable.rounded_square_active);
            else if(pickup.getStatus().equalsIgnoreCase("delivering"))
                binding.pickupStatusDelivering.setBackgroundResource(R.drawable.rounded_square_active);
            else if(pickup.getStatus().equalsIgnoreCase("processing"))
                binding.pickupStatusProcessing.setBackgroundResource(R.drawable.rounded_square_active);
        }
        else{
            binding.progressPickupLocation.setText("-");
            binding.progressDeliveryLocation.setText("-");
        }
    }
}
