package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.MotorResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.EditMotorcycleContract;
import com.alifadepe.android_example.contract.MotorcycleContract;
import com.alifadepe.android_example.interactor.EditMotorcycleInteractor;
import com.alifadepe.android_example.interactor.MotorcycleInteractor;
import com.alifadepe.android_example.model.Motorcycle;

public class EditMotorcyclePresenter implements EditMotorcycleContract.presenter {
    private EditMotorcycleContract.View view;
    private EditMotorcycleInteractor interactor;

    public EditMotorcyclePresenter(EditMotorcycleContract.View view, EditMotorcycleInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void editMotor(int id, Motorcycle motorcycle) {
        interactor.editMotorRequest(id, motorcycle, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String response) {
                view.editMotorSuccess(response);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void setMotor(int id) {
        interactor.showMotorById(id, new RequestCallback<Motorcycle>() {
            @Override
            public void requestSuccess(Motorcycle response) {
                view.setMotor(response);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}
