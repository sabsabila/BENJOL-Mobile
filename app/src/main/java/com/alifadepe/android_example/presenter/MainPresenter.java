package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.contract.MainContract;
import com.alifadepe.android_example.interactor.MainInteractor;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainInteractor interactor;

    public MainPresenter(MainContract.View view, MainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkIsUserLogin() {
        if(interactor.isUserLogin()){
            view.whenUserLogin();
        }
        else {
            view.whenUserNotLogin();
        }
    }
}
