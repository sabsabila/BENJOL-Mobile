package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.ListBookingContract;
import com.benjolteam.benjol.interactor.ListBookingInteractor;
import com.benjolteam.benjol.model.BookingData;

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
