package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.DashboardContract;
import com.benjolteam.benjol.interactor.DashboardInteractor;
import com.benjolteam.benjol.model.BookingData;
import com.benjolteam.benjol.model.Profile;

public class DashboardPresenter implements DashboardContract.Presenter {
    private DashboardContract.View view;
    private DashboardInteractor interactor;

    public DashboardPresenter(DashboardContract.View view, DashboardInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFullname() {
        view.startLoading();
        interactor.getUser(new RequestCallback<Profile>() {

            @Override
            public void requestSuccess(Profile response) {
                view.setUser(response);
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
               view.showError(errorMessage);
               view.endLoading();
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
