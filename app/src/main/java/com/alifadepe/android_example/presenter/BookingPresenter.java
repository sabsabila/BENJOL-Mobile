package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.BookingContract;
import com.alifadepe.android_example.interactor.BookingInteractor;
import com.alifadepe.android_example.model.Booking;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Service;

import java.util.List;

public class BookingPresenter implements BookingContract.Presenter {
    private BookingContract.View view;
    private BookingInteractor interactor;

    public BookingPresenter(BookingContract.View view, BookingInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void book(Booking newBooking) {
        view.startLoading();
        interactor.requestBooking(newBooking, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.endLoading();
                view.bookingSuccess(message);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.bookingFailed(errorMessage);
            }
        });
    }

    @Override
    public void setMotor() {
        interactor.requestMotor(new RequestCallback<List<Motorcycle>>() {
            @Override
            public void requestSuccess(List<Motorcycle> data) {
                view.setMotor(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void setService() {
        interactor.requestService(new RequestCallback<List<Service>>() {
            @Override
            public void requestSuccess(List<Service> data) {
                view.setService(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

}
