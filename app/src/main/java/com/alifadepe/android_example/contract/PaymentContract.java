package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Payment;

public interface PaymentContract {
    interface View {
        void showError(String message);
        void setPayment(Payment payment);
        void startLoading();
        void endLoading();
    }

    interface presenter {
        void setPayment();
    }

    interface Interactor {
        void requestPayment(RequestCallback<Payment> requestCallback);
    }
}
