package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Pickup;

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
