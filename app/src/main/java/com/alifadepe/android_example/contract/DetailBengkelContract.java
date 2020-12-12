package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Bengkel;

import java.util.List;

public interface DetailBengkelContract {
    interface View {
        void showError(String message);
        void setBengkel(Bengkel bengkel);
        void startLoading();
        void endLoading();
        void redirectToBooking();
        void redirectToListSparepart();
    }

    interface Presenter {
        void setBengkel();
        void bookService();
        void searchSparepart();
    }

    interface Interactor {
        void requestBengkel(final RequestCallback<List<Bengkel>> requestCallback);
    }
}
