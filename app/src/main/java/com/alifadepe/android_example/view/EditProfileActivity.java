package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.EditProfileContract;
import com.alifadepe.android_example.databinding.ActivityEditProfileBinding;
import com.alifadepe.android_example.interactor.EditProfileInteractor;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.presenter.EditProfilePresenter;
import com.alifadepe.android_example.util.UtilProvider;

import java.time.LocalDateTime;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View, View.OnClickListener{
    private EditProfileContract.Presenter presenter;
    private ActivityEditProfileBinding binding;
    private String gender = "-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new EditProfilePresenter(this, new EditProfileInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();

    }

    private void initView(){
        presenter.setProfile();
        binding.backButton.setOnClickListener(this);
        binding.saveButton.setOnClickListener(this);
        binding.changePwdButton.setOnClickListener(this);
        binding.radioFemale.setOnClickListener(this);
        binding.radioMale.setOnClickListener(this);
        binding.radioOther.setOnClickListener(this);
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
        if(v.getId() == binding.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.saveButton.getId()){
            onSaveButtonClick();
        }
        if(v.getId() == binding.changePwdButton.getId()){
            onChangePasswordButtonClick();
        }
    }

    private void onChangePasswordButtonClick() {
        finish();
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    private void onSaveButtonClick() {
        int selectedId = binding.radioButton.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        if(radioButton != null)
            gender = radioButton.getText().toString();
        else
            gender = null;
        String birthdate = binding.birthDate.getYear() + "-" + (binding.birthDate.getMonth() + 1) + "-" + binding.birthDate.getDayOfMonth();
        Profile profile = new Profile(binding.editFirstName.getText().toString(),
                binding.editLastName.getText().toString(),
                gender, birthdate,
                binding.editUsername.getText().toString(),
                binding.editEmail.getText().toString(),
                binding.editPhoneNumber.getText().toString());
        presenter.saveProfile(profile);
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
        binding.setUser(user);
        if(user.getGender().equalsIgnoreCase("male"))
            binding.radioMale.setChecked(true);
        else if(user.getGender().equalsIgnoreCase("female"))
            binding.radioFemale.setChecked(true);
        else if(user.getGender().equalsIgnoreCase("other"))
            binding.radioOther.setChecked(true);
    }

}