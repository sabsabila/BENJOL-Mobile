package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alifadepe.android_example.adapter.ListSparepartAdapter;
import com.alifadepe.android_example.contract.SparepartContract;
import com.alifadepe.android_example.databinding.ActivityListSparepartBinding;
import com.alifadepe.android_example.interactor.SparepartInteractor;
import com.alifadepe.android_example.model.Sparepart;
import com.alifadepe.android_example.presenter.SparepartPresenter;
import com.alifadepe.android_example.util.UtilProvider;

import java.util.List;

public class SparepartActivity extends AppCompatActivity implements SparepartContract.View, View.OnClickListener {
    private SparepartContract.presenter presenter;
    private ActivityListSparepartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListSparepartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SparepartPresenter(this, new SparepartInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setSparepart();
        binding.listSparepart.setLayoutManager(new LinearLayoutManager(this));
        binding.searchSparepartButton.setOnClickListener(this);
        binding.baseLayout.pageTitle.setText("Search Sparepart");
        binding.baseLayout.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.searchSparepartButton.getId()){
            onSearchButtonClick();
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

    private void onSearchButtonClick() {
        presenter.setSearchResult(binding.searchView.getText().toString());
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
    public void setSparepart(List<Sparepart> spareparts) {
        if(spareparts.size() > 0)
            binding.listSparepart.setAdapter(new ListSparepartAdapter(spareparts, getLayoutInflater()));
        else
            Toast.makeText(this, "No Spareparts Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSearchresult(List<Sparepart> spareparts) {
        if(spareparts.size()>0)
            binding.listSparepart.setAdapter(new ListSparepartAdapter(spareparts, getLayoutInflater()));
        else
            Toast.makeText(this, "Search Keyword Not Found", Toast.LENGTH_SHORT).show();
    }
}
