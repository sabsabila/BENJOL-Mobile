package com.benjolteam.benjol.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ChatBridgeContract;
import com.benjolteam.android_example.databinding.ActivityChatBridgeBinding;
import com.benjolteam.benjol.interactor.ChatBridgeInteractor;
import com.benjolteam.benjol.model.Bengkel;
import com.benjolteam.benjol.presenter.ChatBridgePresenter;
import com.benjolteam.benjol.util.UtilProvider;
import com.squareup.picasso.Picasso;

public class ChatBridgeActivity extends AppCompatActivity implements ChatBridgeContract.View, View.OnClickListener {
    private ChatBridgeContract.presenter presenter;
    private ActivityChatBridgeBinding binding;
    private static final int REQUEST_CALL = 1;
    private String phoneNumber;
    private int bengkelId;
    private String bengkelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBridgeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bengkelId = intent.getIntExtra("bengkel_id", 0);

        presenter = new ChatBridgePresenter(this, new ChatBridgeInteractor(UtilProvider.getSharedPreferencesUtil(), bengkelId));
        initView();
    }

    private void initView() {
        binding.baseLayout.pageTitle.setText("Chat");
        presenter.setBengkel();
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
        binding.callButton.setOnClickListener(this);
        binding.waButton.setOnClickListener(this);
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
        if (v.getId() == binding.baseLayout.backButton.getId()) {
            onBackButtonClick();
        }
        if (v.getId() == binding.navbar.homeButton.getId()) {
            onHomeButtonClick();
        }
        if(v.getId() == binding.navbar.profileButton.getId()){
            onProfileClick();
        }
        if (v.getId() == binding.callButton.getId()) {
            onCallButtonClick();
        }
        if (v.getId() == binding.waButton.getId()) {
            onWaButtonClick();
        }
    }

    public void onBackButtonClick() {
        Intent intent = new Intent(this, DetailBengkelActivity.class);
        intent.putExtra("bengkel_id", bengkelId);
        finish();
        startActivity(intent);
    }

    private void onHomeButtonClick() {
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void onProfileClick() {
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void onCallButtonClick() {
        if(phoneNumber.trim().length() > 0) {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + phoneNumber;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(dial));
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onCallButtonClick();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onWaButtonClick() {
        boolean installed = checkWaApp("com.whatsapp");
        String headerMessage = "[BENJOL] - Halo, " + bengkelName + "!\n";
        String whatsappUrl = "http://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + headerMessage;

        if(installed) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(whatsappUrl));
            startActivity(intent);
        } else {
            Toast.makeText(this,"WhatsApp not installed on your device", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkWaApp(String url) {
        PackageManager packageManager = getPackageManager();
        boolean appInstalled;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            appInstalled = false;
        }

        return appInstalled;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBengkel(Bengkel bengkel) {
        if(bengkel != null){
            binding.setBengkel(bengkel);
            phoneNumber = bengkel.getPhone_number();
            bengkelName = bengkel.getName();
            if(bengkel.getProfile_picture() != null){
                binding.chatBengkelImage.setBackground(null);
                Picasso.get()
                        .load(ApiConstant.BASE_URL + "/" + bengkel.getProfile_picture())
                        .fit()
                        .into(binding.chatBengkelImage);
            }
        }
    }

}


