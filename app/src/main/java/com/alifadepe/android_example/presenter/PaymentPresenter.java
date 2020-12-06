package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.MotorcycleContract;
import com.alifadepe.android_example.contract.PaymentContract;
import com.alifadepe.android_example.interactor.MotorcycleInteractor;
import com.alifadepe.android_example.interactor.PaymentInteractor;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Payment;

public class PaymentPresenter implements PaymentContract.presenter {
    private PaymentContract.View view;
    private PaymentInteractor interactor;

    public PaymentPresenter(PaymentContract.View view, PaymentInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setPayment() {
        view.startLoading();
        interactor.requestPayment(new RequestCallback<Payment>() {
            @Override
            public void requestSuccess(Payment response) {
                view.setPayment(response);
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
