package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.ProgressServiceContract;
import com.benjolteam.benjol.interactor.ProgressServiceInteractor;

import java.util.List;

public class ProgressServicePresenter implements ProgressServiceContract.Presenter {
    private ProgressServiceContract.View view;
    private ProgressServiceInteractor interactor;

    public ProgressServicePresenter(ProgressServiceContract.View view, ProgressServiceInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setProgressService(int id) {
        view.startLoading();
        interactor.requestProgressService(id, new RequestCallback<List<String>>() {
            @Override
            public void requestSuccess(List<String> progress) {
                view.setProgressService(progress);
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
    public void payService() {
        view.redirectToPayment();
    }

    @Override
    public void cancelBooking(int id) {
        view.startLoading();
        interactor.requestCancelBooking(id, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.cancelSuccess(message);
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }
}
