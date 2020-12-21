package com.alifadepe.android_example.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.UploadProfileImageContract;
import com.alifadepe.android_example.databinding.ActivityUploadImageBinding;
import com.alifadepe.android_example.interactor.UploadProfileImageInteractor;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.presenter.UploadProfileImagePresenter;
import com.alifadepe.android_example.util.UtilProvider;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.File;

public class UploadProfileImageActivity extends AppCompatActivity implements UploadProfileImageContract.View, View.OnClickListener{
    private UploadProfileImageContract.Presenter presenter;
    private ActivityUploadImageBinding binding;
    private String gender = "-";
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new UploadProfileImagePresenter(this, new UploadProfileImageInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();

    }

    private void initView(){
        presenter.setProfile();
        binding.baseLayout.pageTitle.setText("Upload Profile Picture");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.imageHolder.setOnClickListener(this);
        binding.confirmUpload.setOnClickListener(this);
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
    }

    private void onLoadButtonClick() {
        ImagePicker.Companion.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    private void onSaveButtonClick() {
        presenter.saveProfile(file);
    }

    public void onBackButtonClick(){
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editProfileSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void setProfile(Profile user) {
        if(user.getProfile_picture() != null){
            binding.camera.setVisibility(View.GONE);
            binding.imageHolder.setBackground(null);
            Picasso.get()
                    .load(ApiConstant.BASE_URL + "/" + user.getProfile_picture())
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
