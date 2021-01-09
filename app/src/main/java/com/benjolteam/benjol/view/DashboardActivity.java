package com.benjolteam.benjol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.benjolteam.android_example.databinding.ActivityDashboardBinding;
import com.benjolteam.android_example.R;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.DashboardContract;
import com.benjolteam.benjol.interactor.DashboardInteractor;
import com.benjolteam.benjol.model.BookingData;
import com.benjolteam.benjol.model.Profile;
import com.benjolteam.benjol.presenter.DashboardPresenter;
import com.benjolteam.benjol.util.UtilProvider;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

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
        presenter.getFullname();
        presenter.getBookingData();
        binding.navbar.homeButton.setBackgroundResource(R.drawable.home_icon_filled);
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
        startActivity(new Intent(this, ListPickupActivity.class));
    }
    public void onfindSparepartClick(){
        finish();
        startActivity(new Intent(this, SparepartActivity.class));
    }
    public void onCheckProgressClick(){
        finish();
        startActivity(new Intent(this, ListBookingActivity.class));
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
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUser(Profile user){
        binding.profileName.setText(user.getFull_name());
        if(user.getProfile_picture() != null){
            binding.profileImage.setBackground(null);
            Picasso.get()
                    .load(ApiConstant.BASE_URL + "/" + user.getProfile_picture())
                    .fit()
                    .into(binding.profileImage);
        }
    }

    @Override
    public void setBooking(BookingData booking) {
        if(booking != null) {
            binding.setBooking(booking);
            setStatus(booking);
            String[] date = booking.getRepairment_date().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
            CharSequence dateFormatted = DateFormat.format("EEE, d MMM yyyy", calendar);
            binding.dayDate.setText(dateFormatted);
        }else
            binding.bengkelName.setText("No Bookings Made");
    }

    private void setStatus(BookingData booking) {
        if (booking.getStatus().equalsIgnoreCase("upcoming")) {
            binding.status.setText("Upcoming");
            binding.status.setTextColor(getResources().getColor(R.color.colorSecondary));
        } else if (booking.getStatus().equalsIgnoreCase("ongoing")) {
            binding.status.setText("On Going");
            binding.status.setTextColor(getResources().getColor(R.color.ongoing));
        } else if (booking.getStatus().equalsIgnoreCase("finished")) {
            binding.status.setText("Finished");
            binding.status.setTextColor(getResources().getColor(R.color.finish));
        } else if (booking.getStatus().equalsIgnoreCase("canceled")) {
            binding.status.setText("Canceled");
            binding.status.setTextColor(getResources().getColor(R.color.cancel));
        }
    }
}
