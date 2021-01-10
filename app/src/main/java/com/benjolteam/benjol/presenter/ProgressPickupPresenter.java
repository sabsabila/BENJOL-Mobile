package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.ProgressPickupContract;
import com.benjolteam.benjol.interactor.ProgressPickupInteractor;
import com.benjolteam.benjol.model.Pickup;

public class ProgressPickupPresenter implements ProgressPickupContract.Presenter {
    private ProgressPickupContract.View view;
    private ProgressPickupInteractor interactor;

    public ProgressPickupPresenter(ProgressPickupContract.View view, ProgressPickupInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setProgressService(int id) {
        view.startLoading();
        interactor.requestProgressPickup(id, new RequestCallback<Pickup>() {
            @Override
            public void requestSuccess(Pickup pickup) {
                view.setProgressPickup(pickup);
                view.endLoading();
            }
            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }
}
