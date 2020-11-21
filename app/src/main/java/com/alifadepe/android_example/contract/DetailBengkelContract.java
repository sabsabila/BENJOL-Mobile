package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Bengkel;

import java.util.List;

public class DetailBengkelContract {
    public interface View {
        void showError(String message);
        void setBengkel(Bengkel bengkel);
        void redirectToBooking();
        void redirectToListSparepart();
    }

    public interface Presenter {
        void setBengkel();
        void bookService();
        void searchSparepart();
    }

    public interface Interactor {
        void requestBengkel(final RequestCallback<List<Bengkel>> requestCallback);
    }
}
