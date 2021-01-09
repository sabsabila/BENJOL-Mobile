package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benjolteam.benjol.adapter.ListSparepartBengkelAdapter;
import com.benjolteam.benjol.contract.SparepartBengkelContract;
import com.benjolteam.android_example.databinding.ActivityListSparepartBinding;
import com.benjolteam.benjol.interactor.SparepartBengkelInteractor;
import com.benjolteam.benjol.model.Sparepart;
import com.benjolteam.benjol.presenter.SparepartBengkelPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.util.List;

public class SparepartBengkelActivity extends AppCompatActivity implements SparepartBengkelContract.View, View.OnClickListener {
    private SparepartBengkelContract.presenter presenter;
    private ActivityListSparepartBinding binding;
    private int bengkelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListSparepartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bengkelId = intent.getIntExtra("bengkel_id", 0);

        presenter = new SparepartBengkelPresenter(this, new SparepartBengkelInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setSparepart(bengkelId);
        binding.listSparepart.setLayoutManager(new LinearLayoutManager(this));
        binding.searchSparepartButton.setOnClickListener(this);
        binding.baseLayout.pageTitle.setText("List Sparepart");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.searchSparepartButton.getId()){
            onSearchButtonClick();
        }
        if(v.getId() == binding.navbar.profileButton.getId()){
            onProfileClick();
        }
        if(v.getId() == binding.navbar.homeButton.getId()){
            onHomeButtonClick();
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

    private void onSearchButtonClick() {
        presenter.setSearchResult(bengkelId, binding.searchView.getText().toString());
    }

    public void onBackButtonClick(){
        finish();
        Intent intent = new Intent(this, DetailBengkelActivity.class);
        intent.putExtra("bengkel_id", bengkelId);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSparepart(List<Sparepart> spareparts) {
        if(spareparts.size()>0)
            binding.listSparepart.setAdapter(new ListSparepartBengkelAdapter(spareparts, getLayoutInflater()));
        else
            Toast.makeText(this, "No Sparepats Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSearchresult(List<Sparepart> spareparts) {
        if(spareparts.size()>0)
            binding.listSparepart.setAdapter(new ListSparepartBengkelAdapter(spareparts, getLayoutInflater()));
        else
            Toast.makeText(this, "Search Keyword Not Found", Toast.LENGTH_SHORT).show();
    }
}
