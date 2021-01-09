package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.contract.AboutUsContract;

public class AboutUsPresenter implements AboutUsContract.Presenter {
    private AboutUsContract.View view;

    public AboutUsPresenter(AboutUsContract.View view) {
        this.view = view;
    }

    @Override
    public void redirectToSplash() {
        view.backToSplash();
    }
}
