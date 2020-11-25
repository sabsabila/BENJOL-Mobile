package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.interactor.DashboardInteractor;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.Profile;

import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter {
    private DashboardContract.View view;
    private DashboardInteractor interactor;

    public DashboardPresenter(DashboardContract.View view, DashboardInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getUsername() {
        interactor.getUser(new RequestCallback<List<Profile>>() {

            @Override
            public void requestSuccess(List<Profile> response) {
                view.setUsername(response.get(0).getUsername());
            }

            @Override
            public void requestFailed(String errorMessage) {
               view.showError(errorMessage);
            }
        });
    }

    @Override
    public void getBookingData() {
        interactor.getLatestBooking(new RequestCallback<BookingData>() {

            @Override
            public void requestSuccess(BookingData response) {
                view.setBooking(response);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

}
