package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benjolteam.benjol.adapter.ListBengkelAdapter;
import com.benjolteam.benjol.contract.ListBengkelContract;
import com.benjolteam.android_example.databinding.ActivityCariBengkelBinding;
import com.benjolteam.benjol.interactor.ListBengkelInteractor;
import com.benjolteam.benjol.model.Bengkel;
import com.benjolteam.benjol.presenter.ListBengkelPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.util.List;

public class ListBengkelActivity extends AppCompatActivity implements ListBengkelContract.View, View.OnClickListener, ListBengkelAdapter.ListItemClickListener {
    private ListBengkelContract.presenter presenter;
    private ActivityCariBengkelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCariBengkelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ListBengkelPresenter(this, new ListBengkelInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setBengkel();
        binding.listBengkel.setLayoutManager(new LinearLayoutManager(this));
        binding.searchBengkelButton.setOnClickListener(this);
        binding.baseLayout.pageTitle.setText("Search Bengkel");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.searchBengkelButton.getId()){
            onSearchButtonClick();
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

    private void onSearchButtonClick() {
        presenter.setSearchResult(binding.searchBengkel.getText().toString());
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
    public void setBengkel(List<Bengkel> bengkel) {
        if(bengkel.size()>0)
            binding.listBengkel.setAdapter(new ListBengkelAdapter(bengkel, getLayoutInflater(), this));
        else
            Toast.makeText(this, "No Bengkel Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSearchresult(List<Bengkel> bengkel) {
        if(bengkel.size()>0)
            binding.listBengkel.setAdapter(new ListBengkelAdapter(bengkel, getLayoutInflater(), this));
        else
            Toast.makeText(this, "Search Keyword Not Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(this, DetailBengkelActivity.class);
        intent.putExtra("bengkel_id", position);
        finish();
        startActivity(intent);
    }
}
