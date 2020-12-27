package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.databinding.ActivityCheckProgressBinding;
import com.alifadepe.android_example.interactor.ProgressServiceInteractor;
import com.alifadepe.android_example.presenter.ProgressServicePresenter;
import com.alifadepe.android_example.util.UtilProvider;

import java.util.List;

public class ProgressServiceActivity extends AppCompatActivity implements ProgressServiceContract.View, View.OnClickListener {
    private ProgressServiceContract.Presenter presenter;
    private ActivityCheckProgressBinding binding;
    private int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_progress);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bookingId = intent.getIntExtra("booking_id", 0);

        presenter = new ProgressServicePresenter(this, new ProgressServiceInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setProgressService(bookingId);
        binding.baseLayout.pageTitle.setText("Progress Service");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
        binding.paymentDetailButton.setOnClickListener(this);
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
        if(v.getId() == binding.paymentDetailButton.getId()){
            presenter.payService();
        }
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
        startActivity(new Intent(this, ListBookingActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressService(List<String> progress) {
        String estimatedTime;

        if(progress != null){
            if(progress.get(6).equalsIgnoreCase("ongoing")){
                int percentage = Integer.parseInt(progress.get(2));
                int hours = Integer.parseInt(progress.get(3));
                int minutes = Integer.parseInt(progress.get(4));
                if(percentage > 100){
                    percentage = 100;
                    hours = 0;
                    minutes = 0;
                }
                binding.startTime.setText(progress.get(0));
                binding.endTime.setText(progress.get(1));
                binding.percentageProgress.setText(percentage + " %");
                estimatedTime = hours +  " Hours " + minutes + " Minutes";
                binding.estimatedProgress.setText(estimatedTime);
            }else if(progress.get(6).equalsIgnoreCase("upcoming") || progress.get(6).equalsIgnoreCase("canceled")){
                binding.percentageProgress.setText("0 %");
                binding.estimatedProgress.setText("-");
                binding.paymentDetailButton.setVisibility(View.GONE);
                if(progress.get(6).equalsIgnoreCase("canceled")){
                    binding.estimatedProgress.setText("Canceled");
                }
            }
            else {
                binding.percentageProgress.setText("100 %");
                binding.estimatedProgress.setText("Service Finished");
            }
            binding.plateNumber.setText(progress.get(5));
        }else{
            binding.plateNumber.setText("No Bookings Made Yet");
            binding.paymentDetailButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void redirectToPayment() {
        finish();
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("booking_id", bookingId);
        startActivity(intent);
    }
}
