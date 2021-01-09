package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Motorcycle;

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
