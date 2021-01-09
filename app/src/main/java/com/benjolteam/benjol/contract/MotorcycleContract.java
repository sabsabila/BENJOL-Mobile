package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Motorcycle;

public interface MotorcycleContract {
    interface View {
        void showError(String message);
        void addMotorSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface presenter {
        void inputMotor(Motorcycle motorcycle);
    }

    interface Interactor {
        void addMotorRequest(Motorcycle motorcycle, RequestCallback<String> requestCallback);
    }
}
