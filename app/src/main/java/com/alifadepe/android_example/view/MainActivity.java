package com.alifadepe.android_example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.contract.MainContract;
import com.alifadepe.android_example.interactor.MainInteractor;
import com.alifadepe.android_example.presenter.MainPresenter;
import com.alifadepe.android_example.util.UtilProvider;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this, new MainInteractor(UtilProvider.getSharedPreferencesUtil()));
        presenter.checkIsUserLogin();
    }

    @Override
    public void whenUserLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), ListBookActivity.class));
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
