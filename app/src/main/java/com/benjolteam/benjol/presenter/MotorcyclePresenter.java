package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.MotorcycleContract;
import com.benjolteam.benjol.interactor.MotorcycleInteractor;
import com.benjolteam.benjol.model.Motorcycle;

public class MotorcyclePresenter implements MotorcycleContract.presenter {
    private MotorcycleContract.View view;
    private MotorcycleInteractor interactor;

    public MotorcyclePresenter(MotorcycleContract.View view, MotorcycleInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void inputMotor(Motorcycle motorcycle) {
        view.startLoading();
        interactor.addMotorRequest(motorcycle, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String response) {
                view.addMotorSuccess(response);
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
