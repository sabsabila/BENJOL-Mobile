package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.RegisterResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.MotorcycleContract;
import com.alifadepe.android_example.interactor.MotorcycleInteractor;
import com.alifadepe.android_example.model.Motorcycle;

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
