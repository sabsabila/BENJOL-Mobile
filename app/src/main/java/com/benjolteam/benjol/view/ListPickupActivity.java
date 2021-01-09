package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benjolteam.benjol.adapter.ListPickupAdapter;
import com.benjolteam.benjol.contract.ListPickupContract;
import com.benjolteam.android_example.databinding.ActivityListPickupBinding;
import com.benjolteam.benjol.interactor.ListPickupInteractor;
import com.benjolteam.benjol.model.PickupData;
import com.benjolteam.benjol.presenter.ListPickupPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.util.List;

public class ListPickupActivity extends AppCompatActivity implements ListPickupContract.View, View.OnClickListener, ListPickupAdapter.ListItemClickListener {
    private ListPickupContract.Presenter presenter;
    private ActivityListPickupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPickupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ListPickupPresenter(this, new ListPickupInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setPickup();
        binding.listPickup.setLayoutManager(new LinearLayoutManager(this));
        binding.baseLayout.pageTitle.setText("Your Pickups");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
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
    public void setPickup(List<PickupData> pickup) {
        if(pickup.size()>0)
            binding.listPickup.setAdapter(new ListPickupAdapter(pickup, getLayoutInflater(), this));
        else
            Toast.makeText(this, "No Pickups Made Yet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(this, ProgressPickupActivity.class);
        intent.putExtra("booking_id", position);
        finish();
        startActivity(intent);
    }
}
