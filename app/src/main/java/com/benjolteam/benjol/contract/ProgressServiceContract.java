package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;

import java.util.List;

public interface ProgressServiceContract {
    interface View {
        void showError(String message);
        void setProgressService(List<String> progress);
        void cancelSuccess(String message);
        void showCancelAlert(int id);
        void redirectToPayment();
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setProgressService(int id);
        void payService();
        void cancelBooking(int id);
    }

    interface Interactor {
        void requestProgressService(int id, final RequestCallback<List<String>> requestCallback);
        void requestCancelBooking(int id, final RequestCallback<String> requestCallback);
    }
}
