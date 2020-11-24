package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.BookingContract;
import com.alifadepe.android_example.databinding.ActivityBookingBinding;
import com.alifadepe.android_example.interactor.BookingInteractor;
import com.alifadepe.android_example.model.Booking;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Service;
import com.alifadepe.android_example.presenter.BookingPresenter;
import com.alifadepe.android_example.util.UtilProvider;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements BookingContract.View, View.OnClickListener {
    private BookingContract.Presenter presenter;
    private ActivityBookingBinding binding;
    private String isPickUp;
    private String pickUpLocation;
    private String dropOffLocation;
    private String problem;
    private int bengkelId;
    private int selectedMotor;
    private int selectedService;
    private static final int PICKUP_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // int bengkel_id = intent.getIntExtra("bengkel_id"); => ini dapet dari page detail bengkel
        bengkelId = 1;
        presenter = new BookingPresenter(this, new BookingInteractor(UtilProvider.getSharedPreferencesUtil(), bengkelId));
        initView();
    }

    private void initView(){
        binding.baseLayout.pageTitle.setText("Booking");
        binding.baseLayout.backButton.setOnClickListener(this);
        presenter.setMotor();
        presenter.setService();
        binding.yesButton.setOnClickListener(this);
        binding.yesButton.setOnClickListener(this);
        binding.noButton.setOnClickListener(this);
        binding.bookButton.setOnClickListener(this);
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
    public void bookingSuccess(String message) {
        finish();
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DashboardActivity.class)); //ganti ke paymentkalauudah
    }

    @Override
    public void bookingFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackClick();
        }
        if(v.getId() == binding.yesButton.getId()){
            onButtonYesClick();
        }
        if(v.getId() == binding.noButton.getId()){
            onButtonNoClick();
        }
        if(v.getId() == binding.bookButton.getId()){
            onButtonBookClick();
        }
    }

    private void onBackClick() {
        Intent intent = new Intent(this, DetailBengkelActivity.class);
        intent.putExtra("bengkel_id", bengkelId);
        finish();
        startActivity(intent);
    }

    public void onButtonYesClick(){
        isPickUp = "Yes";
        Intent intent = new Intent(this, PickupActivity.class);
        startActivityForResult(intent, PICKUP_ACTIVITY_REQUEST_CODE);
    }

    public void onButtonNoClick(){
        isPickUp = "No";
        pickUpLocation = "-";
        dropOffLocation = "-";
        Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show();
    }

    public void onButtonBookClick(){
        problem = binding.selectService.getSelectedItem().toString() + " - " +
                  binding.etProblem.getText().toString();
        selectedMotor = (int) binding.selectService.getSelectedItemPosition() + 1;
        selectedService = (int) binding.selectMotor.getSelectedItemPosition() + 1;

        Booking booking = new Booking(
                bengkelId,
                selectedMotor,
                binding.etBookingDate.getText().toString(),
                problem,
                isPickUp,
                pickUpLocation,
                dropOffLocation,
                selectedService
        );

        presenter.book(booking);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICKUP_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                pickUpLocation = data.getStringArrayListExtra("location").get(0);
                dropOffLocation = data.getStringArrayListExtra("location").get(1);
            }
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMotor(List<Motorcycle> motorcycles) {
        ArrayAdapter<Motorcycle> adapter =
                new ArrayAdapter<Motorcycle>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, motorcycles);

        binding.selectMotor.setAdapter(adapter);
    }

    @Override
    public void setService(List<Service> services) {
        ArrayAdapter<Service> adapter =
                new ArrayAdapter<Service>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, services);

        binding.selectService.setAdapter(adapter);
    }


}
