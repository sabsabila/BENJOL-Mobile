package com.alifadepe.android_example.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.adapter.ListSparepartAdapter;
import com.alifadepe.android_example.contract.ListSparepartContract;
import com.alifadepe.android_example.databinding.ActivityListSparepartBinding;
//import com.alifadepe.android_example.databinding.ItemSparepartBinding;
import com.alifadepe.android_example.interactor.ListSparepartInteractor;
import com.alifadepe.android_example.model.Sparepart;
import com.alifadepe.android_example.presenter.ListSparepartPresenter;
import com.alifadepe.android_example.util.UtilProvider;

import java.util.List;

public class ListSparepartActivity extends AppCompatActivity implements ListSparepartContract.View, View.OnClickListener {
    private ActivityListSparepartBinding binding;
    private ListSparepartPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        binding = ActivityListSparepartBinding.inflate(getLayoutInflater());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_sparepart);
//        ItemSparepartBinding binding = DataBindingUtil.inflate(layoutInflater.from(parent.getContext()), R.layout.item_sparepart,parent,false);
        setContentView(binding.getRoot());
        int sparepart_id = 1;
        presenter = new ListSparepartPresenter(this, new ListSparepartInteractor(UtilProvider.getSharedPreferencesUtil(),sparepart_id));
        initView();
        presenter.requestListSparepart();
    }

    private void initView(){
        binding.rvSparepart.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.searchView.setVisibility(View.VISIBLE);
        binding.baseLayout.pageTitle.setText("List Sparepart");
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rvSparepart.setVisibility(View.GONE);
        binding.searchView.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvSparepart.setVisibility(View.VISIBLE);
        binding.searchView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListSparepart(List<Sparepart> spareparts) {
        binding.rvSparepart.setAdapter(new ListSparepartAdapter(spareparts, getLayoutInflater(),this));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.searchView.getId()){
            searchItem();
        }
    }

    private void searchItem(){}


}
