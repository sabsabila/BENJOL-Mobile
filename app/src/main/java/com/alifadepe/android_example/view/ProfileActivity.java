package com.alifadepe.android_example.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.ProfileContract;
import com.alifadepe.android_example.databinding.ActivityProfileBinding;
import com.alifadepe.android_example.interactor.ProfileInteractor;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.presenter.ProfilePresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, View.OnClickListener {
    private ProfileContract.presenter presenter;
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        setContentView(binding.getRoot());

        presenter = new ProfilePresenter(this, new ProfileInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setProfile();
        binding.signOutButton.setOnClickListener(this);
        binding.inputMotorButton.setOnClickListener(this);
        binding.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.signOutButton.getId()){
            onButtonSignOutClick();
        }
        if(v.getId() == binding.inputMotorButton.getId()){
            onButtonInputMotorClick();
        }
        if(v.getId() == binding.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.navbar.homeButton.getId()){
            onHomeButtonClick();
        }
        if(v.getId() == binding.profile.getId()){
            onEditProfileClick();
        }
    }

    private void onHomeButtonClick() {
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void onEditProfileClick() {
        finish();
        startActivity(new Intent(this, EditProfileActivity.class));
    }

    public void onButtonSignOutClick(){
        showAlertDialog();
    }

    public void onButtonInputMotorClick(){
        startActivity(new Intent(this, MotorcycleActivity.class));
    }

    public void onBackButtonClick(){
        startActivity(new Intent(this, DashboardActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProfile(Profile user) {
        binding.profileName.setText(user.getFirst_name() + " " + user.getLast_name());
        binding.setUser(user);
    }

    @Override
    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Sign Out");
        builder.setMessage("Are you sure you want to Sign Out ?");
        builder.setPositiveButton(Html.fromHtml("<font color='#FBB308'>Yes</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                presenter.logout();
            }
        });
        builder.setNegativeButton(Html.fromHtml("<font color='#eb5334'>No</font>"), null);
        builder.create();
        builder.show();
    }

    @Override
    public void redirectToLogin() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
