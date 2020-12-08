package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ListBookingContract;
import com.alifadepe.android_example.contract.ListPickupContract;
import com.alifadepe.android_example.interactor.ListBookingInteractor;
import com.alifadepe.android_example.interactor.ListPickupInteractor;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.PickupData;

import java.util.List;

public class ListPickupPresenter implements ListPickupContract.Presenter {
    private ListPickupContract.View view;
    private ListPickupInteractor interactor;

    public ListPickupPresenter(ListPickupContract.View view, ListPickupInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setPickup() {
        view.startLoading();
        interactor.requestPickups(new RequestCallback<List<PickupData>>() {
            @Override
            public void requestSuccess(List<PickupData> data) {
                view.endLoading();
                view.setPickup(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
