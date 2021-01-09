package com.benjolteam.benjol.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benjolteam.android_example.R;
import com.benjolteam.benjol.adapter.ListMotorAdapter;
import com.benjolteam.benjol.contract.ProfileContract;
import com.benjolteam.android_example.databinding.ActivityProfileBinding;
import com.benjolteam.benjol.interactor.ProfileInteractor;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.model.Profile;
import com.benjolteam.benjol.presenter.ProfilePresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.util.Calendar;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, View.OnClickListener, ListMotorAdapter.ListItemClickListener, ListMotorAdapter.ListItemLongClickListener {
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
        presenter.setMotor();
        binding.navbar.profileButton.setBackgroundResource(R.drawable.profile_icon_filled);
        binding.listMotorcycle.setLayoutManager(new LinearLayoutManager(this));
        binding.signOutButton.setOnClickListener(this);
        binding.inputMotorButton.setOnClickListener(this);
        binding.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.profile.setOnClickListener(this);
        binding.uploadProfileButton.setOnClickListener(this);
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
        if(v.getId() == binding.uploadProfileButton.getId()){
            onUploadProfileClick();
        }
    }

    private void onUploadProfileClick() {
        finish();
        startActivity(new Intent(this, UploadProfileImageActivity.class));
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
        finish();
        startActivity(new Intent(this, MotorcycleActivity.class));
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
    public void setProfile(Profile user) {
        binding.profileName.setText(user.getFull_name());
        binding.setUser(user);
        if(user.getGender() != null)
            binding.gender.setText(user.getGender());
        if(user.getBirth_date()!=null){
            String[] date = user.getBirth_date().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
            CharSequence dateFormatted = DateFormat.format("EEE, d MMM yyyy", calendar);
            binding.birthDate.setText(dateFormatted);
        }
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
    public void showDeleteAlert(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Delete Item");
        builder.setMessage("Are you sure you want to delete this item ?");
        builder.setPositiveButton(Html.fromHtml("<font color='#FBB308'>Yes</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                presenter.deleteMotor(id);
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

    @Override
    public void setMotor(List<Motorcycle> motorcycles) {
        binding.listMotorcycle.setAdapter(new ListMotorAdapter(motorcycles, getLayoutInflater(), this, this));
    }

    @Override
    public void editMotor(int id) {
        Intent intent = new Intent(this, EditMotorcycleActivity.class);
        intent.putExtra("id", id);
        finish();
        startActivity(intent);
    }

    @Override
    public void deleteSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onListItemClick(int position) {
        presenter.editMotor(position);
    }

    @Override
    public boolean onListItemLongClick(int position) {
        showDeleteAlert(position);
        return true;
    }
}
