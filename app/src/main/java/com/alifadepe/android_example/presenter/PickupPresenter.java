package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.contract.PickupContract;

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
