package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.contract.SplashContract;

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View view;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void redirectToApp() {
        view.startApp();
    }

    @Override
    public void redirectToAboutUs() {
        view.showAboutUs();
    }
}
