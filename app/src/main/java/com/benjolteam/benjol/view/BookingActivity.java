package com.benjolteam.benjol.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.benjolteam.android_example.R;
import com.benjolteam.benjol.contract.BookingContract;
import com.benjolteam.android_example.databinding.ActivityBookingBinding;
import com.benjolteam.benjol.interactor.BookingInteractor;
import com.benjolteam.benjol.model.Booking;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.model.Service;
import com.benjolteam.benjol.presenter.BookingPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.util.Calendar;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements BookingContract.View, View.OnClickListener {
    private BookingContract.Presenter presenter;
    private ActivityBookingBinding binding;
    private String isPickUp = "No";
    private String pickUpLocation = "-";
    private String dropOffLocation = "-";
    private String bookingDate;
    private String problem;
    private int bengkelId;
    private Motorcycle selectedMotor;
    private Service selectedService;
    private static final int PICKUP_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bengkelId = intent.getIntExtra("bengkel_id", 0);

        presenter = new BookingPresenter(this, new BookingInteractor(UtilProvider.getSharedPreferencesUtil(), bengkelId));
        initView();
    }

    private void initView(){
        binding.pageTitle.setText("Booking");
        binding.backButton.setOnClickListener(this);
        presenter.setMotor();
        presenter.setService();
        binding.yesButton.setOnClickListener(this);
        binding.yesButton.setOnClickListener(this);
        binding.bookButton.setOnClickListener(this);
        binding.bookingDate.setOnClickListener(this);
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
        if(v.getId() == binding.backButton.getId()){
            onBackClick();
        }
        if(v.getId() == binding.yesButton.getId()){
            onButtonYesClick();
        }
        if(v.getId() == binding.bookButton.getId()){
            onButtonBookClick();
        }
        if(v.getId() == binding.bookingDate.getId()){
            onBookingDateClick();
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

    private void onBookingDateClick() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month-1);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                bookingDate = year+"-"+month+"-"+day;
                CharSequence date = DateFormat.format("EEE, d MMM yyyy", calendar);
                binding.bookingDate.setText(date);
            }
        }, year, month, day);
        final long today = System.currentTimeMillis() - 1000;
        datePickerDialog.getDatePicker().setMinDate(today);
        datePickerDialog.show();
    }

    private void onBackClick() {
        Intent intent = new Intent(this, DetailBengkelActivity.class);
        intent.putExtra("bengkel_id", bengkelId);
        finish();
        startActivity(intent);
    }

    public void onButtonYesClick(){
        Intent intent = new Intent(this, PickupActivity.class);
        startActivityForResult(intent, PICKUP_ACTIVITY_REQUEST_CODE);
    }

    public void onButtonBookClick(){
        if( binding.etProblem.getText().toString().isEmpty())
            Toast.makeText(this, "Please fill in all fields correctly before proceeding.", Toast.LENGTH_SHORT).show();
        else{
            problem = binding.selectService.getSelectedItem().toString() + " - " +
                    binding.etProblem.getText().toString();
            selectedMotor = (Motorcycle) binding.selectMotor.getSelectedItem();
            selectedService = (Service) binding.selectService.getSelectedItem();

            Booking booking = new Booking(
                    bengkelId,
                    selectedMotor.getMotorcycle_id(),
                    bookingDate,
                    problem,
                    isPickUp,
                    pickUpLocation,
                    dropOffLocation,
                    selectedService.getService_id()
            );

            presenter.book(booking);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICKUP_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                isPickUp = "Yes";
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
