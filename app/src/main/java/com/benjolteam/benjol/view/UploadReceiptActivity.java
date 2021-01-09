package com.benjolteam.benjol.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.UploadReceiptContract;
import com.benjolteam.android_example.databinding.ActivityUploadImageBinding;
import com.benjolteam.benjol.interactor.UploadReceiptInteractor;
import com.benjolteam.benjol.model.Payment;
import com.benjolteam.benjol.presenter.UploadReceiptPresenter;
import com.benjolteam.benjol.util.UtilProvider;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.File;

public class UploadReceiptActivity extends AppCompatActivity implements UploadReceiptContract.View, View.OnClickListener{
    private UploadReceiptContract.Presenter presenter;
    private ActivityUploadImageBinding binding;
    private String gender = "-";
    private File file;
    private int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bookingId = intent.getIntExtra("booking_id", 0);

        presenter = new UploadReceiptPresenter(this, new UploadReceiptInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();

    }

    private void initView(){
        presenter.setReceipt(bookingId);
        binding.baseLayout.pageTitle.setText("Upload Receipt");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.imageHolder.setOnClickListener(this);
        binding.confirmUpload.setOnClickListener(this);
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
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.imageHolder.getId()){
            onLoadButtonClick();
        }
        if(v.getId() == binding.confirmUpload.getId()){
            onSaveButtonClick();
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

    private void onLoadButtonClick() {
        ImagePicker.Companion.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    private void onSaveButtonClick() {
        if(file != null)
            presenter.saveReceipt(bookingId, file);
        else
            Toast.makeText(this, "Please select a picture.", Toast.LENGTH_SHORT).show();
    }

    public void onBackButtonClick(){
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("booking_id", bookingId);
        finish();
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("booking_id", bookingId);
        finish();
        startActivity(intent);
    }

    @Override
    public void setReceipt(Payment payment) {
        if(payment.getReceipt() != null){
            binding.camera.setVisibility(View.GONE);
            binding.imageHolder.setBackground(null);
            Picasso.get()
                    .load(ApiConstant.BASE_URL + "/" + payment.getReceipt())
                    .fit()
                    .into(binding.imageHolder);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            binding.camera.setVisibility(View.GONE);
            binding.imageHolder.setBackground(null);
            binding.imageHolder.setImageURI(uri);
            file = ImagePicker.Companion.getFile(data);
        }else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
