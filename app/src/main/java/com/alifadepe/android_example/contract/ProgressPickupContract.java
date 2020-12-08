package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Pickup;

import java.text.ParseException;
import java.util.List;

public interface ProgressPickupContract {
    interface View {
        void showError(String message);
        void setProgressPickup(Pickup pickup);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setProgressService(int id);
    }

    interface Interactor {
        void requestProgressPickup(int id, final RequestCallback<Pickup> requestCallback);
    }
}
