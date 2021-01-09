package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.contract.PickupContract;

import java.util.ArrayList;

public class PickupPresenter implements PickupContract.Presenter {
    private PickupContract.View view;

    public PickupPresenter(PickupContract.View view) {
        this.view = view;
    }

    @Override
    public void inputLocation(ArrayList<String> location) {
        view.startLoading();
        view.sendLocation(location);
    }
}
