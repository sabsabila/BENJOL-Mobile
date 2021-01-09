package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Payment;

public interface PaymentContract {
    interface View {
        void showError(String message);
        void setPayment(Payment payment);
        void startLoading();
        void endLoading();
    }

    interface presenter {
        void setPayment(int id);
    }

    interface Interactor {
        void requestPayment(int id, RequestCallback<Payment> requestCallback);
    }
}
