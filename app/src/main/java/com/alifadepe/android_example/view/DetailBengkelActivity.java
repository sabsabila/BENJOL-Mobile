package com.alifadepe.android_example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.DetailBengkelContract;
import com.alifadepe.android_example.databinding.ActivityDetailBengkelBinding;
import com.alifadepe.android_example.interactor.DetailBengkelInteractor;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.presenter.DetailBengkelPresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class DetailBengkelActivity extends AppCompatActivity implements DetailBengkelContract.View, View.OnClickListener {
    private DetailBengkelContract.Presenter presenter;
    private ActivityDetailBengkelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_bengkel);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int bengkel_id = intent.getIntExtra("bengkel_id", 0);

        presenter = new DetailBengkelPresenter(this, new DetailBengkelInteractor(UtilProvider.getSharedPreferencesUtil(), bengkel_id));
        initView();
    }

    private void initView(){
        presenter.setBengkel();
        binding.baseLayout.pageTitle.setText("Bengkel Info");
        binding.bookServiceButton.setOnClickListener(this);
        binding.listSparepartButton.setOnClickListener(this);
        binding.baseLayout.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.bookServiceButton.getId()){
            presenter.bookService();
        }
        if(v.getId() == binding.listSparepartButton.getId()){
            presenter.searchSparepart();
        }
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
    }

    private void onBackButtonClick() {
        finish();
        startActivity(new Intent(this, ListBengkelActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBengkel(Bengkel bengkel) {
        binding.setBengkel(bengkel);
    }

    @Override
    public void redirectToBooking() {
//        finish();
//        startActivity(new Intent(this, BookServiceActivity.class));
    }

    @Override
    public void redirectToListSparepart() {
//        finish();
//        startActivity(new Intent(this, ListSparepartActivity.class));
    }
}
