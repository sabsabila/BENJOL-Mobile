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
    private int bengkel_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_bengkel);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bengkel_id = intent.getIntExtra("bengkel_id", 0);

        presenter = new DetailBengkelPresenter(this, new DetailBengkelInteractor(UtilProvider.getSharedPreferencesUtil(), bengkel_id));
        initView();
    }

    private void initView(){
        presenter.setBengkel();
        binding.baseLayout.pageTitle.setText("Bengkel Info");
        binding.bookServiceButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
        binding.listSparepartButton.setOnClickListener(this);
        binding.chatBridgeButton.setOnClickListener(this);
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
        if(v.getId() == binding.chatBridgeButton.getId()){
            onchatButtonClick();
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

    private void onchatButtonClick() {
        Intent intent = new Intent(this, ChatBridgeActivity.class);
        intent.putExtra("bengkel_id", bengkel_id);
        finish();
        startActivity(intent);
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
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("bengkel_id", bengkel_id);
        finish();
        startActivity(intent);
    }

    @Override
    public void redirectToListSparepart() {
        Intent intent = new Intent(this, SparepartBengkelActivity.class);
        intent.putExtra("bengkel_id", bengkel_id);
        finish();
        startActivity(intent);
    }
}
