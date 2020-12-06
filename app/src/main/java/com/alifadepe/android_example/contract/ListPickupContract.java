package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.PickupData;

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
