package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benjolteam.benjol.adapter.ListBookingAdapter;
import com.benjolteam.benjol.contract.ListBookingContract;
import com.benjolteam.android_example.databinding.ActivityListBookingBinding;
import com.benjolteam.benjol.interactor.ListBookingInteractor;
import com.benjolteam.benjol.model.BookingData;
import com.benjolteam.benjol.presenter.ListBookingPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.util.List;

public class ListBookingActivity extends AppCompatActivity implements ListBookingContract.View, View.OnClickListener, ListBookingAdapter.ListItemClickListener {
    private ListBookingContract.Presenter presenter;
    private ActivityListBookingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ListBookingPresenter(this, new ListBookingInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setBooking();;
        binding.listBooking.setLayoutManager(new LinearLayoutManager(this));
        binding.baseLayout.pageTitle.setText("Check Progress");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
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

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    public void onBackButtonClick(){
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBooking(List<BookingData> booking) {
        if(booking.size()>0)
            binding.listBooking.setAdapter(new ListBookingAdapter(booking, getLayoutInflater(), this, this));
        else
            Toast.makeText(this, "No Bookings Made Yet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(this, ProgressServiceActivity.class);
        intent.putExtra("booking_id", position);
        finish();
        startActivity(intent);
    }
}
