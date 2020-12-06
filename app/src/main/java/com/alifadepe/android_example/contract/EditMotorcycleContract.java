package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Motorcycle;

public interface EditMotorcycleContract {
    interface View {
        void showError(String message);
        void setMotor(Motorcycle motorcycle);
        void editMotorSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface presenter {
        void editMotor(int id, Motorcycle motorcycle);
        void setMotor(int id);
    }

    interface Interactor {
        void showMotorById(int id, RequestCallback<Motorcycle> requestCallback);
        void editMotorRequest(int id, Motorcycle motorcycle, RequestCallback<String> requestCallback);
    }
}
