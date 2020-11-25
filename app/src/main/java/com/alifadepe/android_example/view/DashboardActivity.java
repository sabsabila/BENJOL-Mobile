package com.alifadepe.android_example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.databinding.ActivityDashboardBinding;
import com.alifadepe.android_example.interactor.DashboardInteractor;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.Sparepart;
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
        presenter.getUsername();
        presenter.getBookingData();
        binding.findBengkel.setOnClickListener(this);
        binding.findSparepart.setOnClickListener(this);
        binding.trackDelivery.setOnClickListener(this);
        binding.checkProgress.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.findBengkel.getId()){
            onfindBengkelClick();
        }
        if(v.getId() == binding.findSparepart.getId()){
            onfindSparepartClick();
        }
        if(v.getId() == binding.trackDelivery.getId()){
            onTrackDeliveryClick();
        }
        if(v.getId() == binding.checkProgress.getId()){
            onCheckProgressClick();
        }
        if(v.getId() == binding.navbar.profileButton.getId()){
            onProfileClick();
        }

    }

    public void onfindBengkelClick(){
        finish();
        startActivity(new Intent(this, ListBengkelActivity.class));
    }
    public void onTrackDeliveryClick(){
        finish();
        startActivity(new Intent(this, ProgressPickupActivity.class));
    }
    public void onfindSparepartClick(){
        finish();
        startActivity(new Intent(this, SparepartActivity.class));
    }
    public void onCheckProgressClick(){
        finish();
        startActivity(new Intent(this, ProgressServiceActivity.class));
    }
    public void onProfileClick(){
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsername(String name){
        binding.profileName.setText(name);
    }

    @Override
    public void setBooking(BookingData booking) {
        if(booking != null)
            binding.setBooking(booking);
        else
            binding.bengkelName.setText("No Bookings Made");
    }
}
