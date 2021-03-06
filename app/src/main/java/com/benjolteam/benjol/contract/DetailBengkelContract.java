package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Bengkel;

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
