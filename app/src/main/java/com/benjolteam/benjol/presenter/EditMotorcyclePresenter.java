package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.EditMotorcycleContract;
import com.benjolteam.benjol.interactor.EditMotorcycleInteractor;
import com.benjolteam.benjol.model.Motorcycle;

public class EditMotorcyclePresenter implements EditMotorcycleContract.presenter {
    private EditMotorcycleContract.View view;
    private EditMotorcycleInteractor interactor;

    public EditMotorcyclePresenter(EditMotorcycleContract.View view, EditMotorcycleInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void editMotor(int id, Motorcycle motorcycle) {
        view.startLoading();
        interactor.editMotorRequest(id, motorcycle, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String response) {
                view.editMotorSuccess(response);
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }

    @Override
    public void setMotor(int id) {
        view.startLoading();
        interactor.showMotorById(id, new RequestCallback<Motorcycle>() {
            @Override
            public void requestSuccess(Motorcycle response) {
                view.setMotor(response);
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
