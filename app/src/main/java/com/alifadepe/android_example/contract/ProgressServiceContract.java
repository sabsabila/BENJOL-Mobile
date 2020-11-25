package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.ProgressService;

import java.text.ParseException;
import java.util.List;

public interface ProgressServiceContract {
    interface View {
        void showError(String message);
        void setProgressService(List<String> progress) throws ParseException;
        void redirectToPayment();
    }

    interface Presenter {
        void setProgressService();
        void payService();
    }

    interface Interactor {
        void requestProgressService(final RequestCallback<List<String>> requestCallback);
    }
}
