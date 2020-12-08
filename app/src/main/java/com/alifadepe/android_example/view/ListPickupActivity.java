package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alifadepe.android_example.adapter.ListBookingAdapter;
import com.alifadepe.android_example.adapter.ListPickupAdapter;
import com.alifadepe.android_example.contract.ListBookingContract;
import com.alifadepe.android_example.contract.ListPickupContract;
import com.alifadepe.android_example.databinding.ActivityListBookingBinding;
import com.alifadepe.android_example.databinding.ActivityListPickupBinding;
import com.alifadepe.android_example.interactor.ListBookingInteractor;
import com.alifadepe.android_example.interactor.ListPickupInteractor;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.PickupData;
import com.alifadepe.android_example.presenter.ListBookingPresenter;
import com.alifadepe.android_example.presenter.ListPickupPresenter;
import com.alifadepe.android_example.util.UtilProvider;

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
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
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
            Toast.makeText(this, "No On Going Pickups", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(this, ProgressPickupActivity.class);
        intent.putExtra("booking_id", position);
        finish();
        startActivity(intent);
    }
}
