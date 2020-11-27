package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.databinding.ActivityCheckProgressBinding;
import com.alifadepe.android_example.interactor.ProgressServiceInteractor;
import com.alifadepe.android_example.model.ProgressService;
import com.alifadepe.android_example.presenter.ProgressServicePresenter;
import com.alifadepe.android_example.util.UtilProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProgressServiceActivity extends AppCompatActivity implements ProgressServiceContract.View, View.OnClickListener {
    private ProgressServiceContract.Presenter presenter;
    private ActivityCheckProgressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_progress);
        setContentView(binding.getRoot());

        presenter = new ProgressServicePresenter(this, new ProgressServiceInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setProgressService();
        binding.baseLayout.pageTitle.setText("Progress Service");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
        binding.paymentDetailButton.setOnClickListener(this);
        binding.baseLayout.backButton.setOnClickListener(this);
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
        startActivity(new Intent(this, DashboardActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressService(List<String> progress) {
        String estimatedTime;

        if(progress != null){
            if(progress.get(0) != null && progress.get(1) != null && progress.get(2) != null){
                int percentage = Integer.parseInt(progress.get(0));
                int hours = Integer.parseInt(progress.get(1));
                int minutes = Integer.parseInt(progress.get(2));
                if(percentage > 100){
                    percentage = 100;
                    hours = 0;
                    minutes = 0;
                }
                binding.percentageProgress.setText(percentage + " %");
                estimatedTime = hours +  " Hours " + minutes + " Minutes";
                binding.estimatedProgress.setText(estimatedTime);
            }else{
                binding.percentageProgress.setText("0 %");
                binding.estimatedProgress.setText("-");
            }
            binding.plateNumber.setText(progress.get(3));
        }else{
            binding.plateNumber.setText("No Bookings Made Yet");
        }
    }

    @Override
    public void redirectToPayment() {
        finish();
        startActivity(new Intent(this, PaymentActivity.class));
    }
}
