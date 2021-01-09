package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.PickupData;

import java.util.List;

public interface ListPickupContract {
    interface View {
        void showError(String message);
        void setPickup(List<PickupData> pickups);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setPickup();
    }

    interface Interactor {
        void requestPickups(final RequestCallback<List<PickupData>> requestCallback);
    }
}
