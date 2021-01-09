package com.benjolteam.benjol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.benjolteam.android_example.R;
import com.benjolteam.benjol.contract.MainContract;
import com.benjolteam.benjol.interactor.MainInteractor;
import com.benjolteam.benjol.presenter.MainPresenter;
import com.benjolteam.benjol.util.UtilProvider;

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
        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
