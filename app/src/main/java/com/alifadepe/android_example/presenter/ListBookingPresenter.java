package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ListBengkelContract;
import com.alifadepe.android_example.contract.ListBookingContract;
import com.alifadepe.android_example.interactor.ListBengkelInteractor;
import com.alifadepe.android_example.interactor.ListBookingInteractor;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.BookingData;

import java.util.List;

public class ListBookingPresenter implements ListBookingContract.Presenter {
    private ListBookingContract.View view;
    private ListBookingInteractor interactor;

    public ListBookingPresenter(ListBookingContract.View view, ListBookingInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setBooking() {
        view.startLoading();
        interactor.requestBookings(new RequestCallback<List<BookingData>>() {
            @Override
            public void requestSuccess(List<BookingData> data) {
                view.endLoading();
                view.setBooking(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
