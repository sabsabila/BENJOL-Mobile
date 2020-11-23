package com.alifadepe.android_example.view;

import android.os.Bundle;
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
import java.util.Date;
import java.util.List;

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
        binding.paymentDetailButton.setOnClickListener(this);
        binding.baseLayout.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.paymentDetailButton.getId()){
            presenter.payService();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressService(List<String> progress) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date startTime = format.parse(progress.get(0));
        Date currentTime = format.parse(String.valueOf(java.util.Calendar.getInstance().getTime()));
        Date endTime = format.parse(progress.get(1));

        long percentage = (endTime.getTime() - currentTime.getTime()) / (endTime.getTime() - startTime.getTime()) * 100;
        long estimated = endTime.getTime() - currentTime.getTime();

        binding.percentageProgress.setText(percentage + "%");
        binding.estimatedProgress.setText("- " +estimated + " -");
        binding.plateNumber.setText(progress.get(2));
    }

    @Override
    public void redirectToPayment() {
//        finish();
//        startActivity(new Intent(this, PaymentActivity.class));
    }
}
