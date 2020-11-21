package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Motorcycle;

import java.util.List;

public interface MotorcycleContract {
    interface View {
        void showError(String message);
        void addMotorSuccess(String message);
    }

    interface presenter {
        void inputMotor(Motorcycle motorcycle);
    }

    interface Interactor {
        void addMotorRequest(Motorcycle motorcycle, RequestCallback<String> requestCallback);
    }
}
