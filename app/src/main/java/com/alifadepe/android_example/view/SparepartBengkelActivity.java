package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alifadepe.android_example.adapter.ListSparepartAdapter;
import com.alifadepe.android_example.adapter.ListSparepartBengkelAdapter;
import com.alifadepe.android_example.contract.SparepartBengkelContract;
import com.alifadepe.android_example.contract.SparepartContract;
import com.alifadepe.android_example.databinding.ActivityListSparepartBinding;
import com.alifadepe.android_example.interactor.SparepartBengkelInteractor;
import com.alifadepe.android_example.interactor.SparepartInteractor;
import com.alifadepe.android_example.model.Sparepart;
import com.alifadepe.android_example.presenter.SparepartBengkelPresenter;
import com.alifadepe.android_example.presenter.SparepartPresenter;
import com.alifadepe.android_example.util.UtilProvider;

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
        binding.listSparepart.setAdapter(new ListSparepartBengkelAdapter(spareparts, getLayoutInflater()));
    }

    @Override
    public void setSearchresult(List<Sparepart> spareparts) {
        if(spareparts.size()>0)
            binding.listSparepart.setAdapter(new ListSparepartBengkelAdapter(spareparts, getLayoutInflater()));
        else
            Toast.makeText(this, "Search Keyword Not Found", Toast.LENGTH_SHORT).show();
    }
}
