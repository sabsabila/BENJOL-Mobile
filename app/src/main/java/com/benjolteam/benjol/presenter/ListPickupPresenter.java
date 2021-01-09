package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.ListPickupContract;
import com.benjolteam.benjol.interactor.ListPickupInteractor;
import com.benjolteam.benjol.model.PickupData;

import java.util.List;

public class ListPickupPresenter implements ListPickupContract.Presenter {
    private ListPickupContract.View view;
    private ListPickupInteractor interactor;

    public ListPickupPresenter(ListPickupContract.View view, ListPickupInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setPickup() {
        view.startLoading();
        interactor.requestPickups(new RequestCallback<List<PickupData>>() {
            @Override
            public void requestSuccess(List<PickupData> data) {
                view.endLoading();
                view.setPickup(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
