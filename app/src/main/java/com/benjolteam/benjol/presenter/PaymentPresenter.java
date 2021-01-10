package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.PaymentContract;
import com.benjolteam.benjol.interactor.PaymentInteractor;
import com.benjolteam.benjol.model.Payment;

public class PaymentPresenter implements PaymentContract.presenter {
    private PaymentContract.View view;
    private PaymentInteractor interactor;

    public PaymentPresenter(PaymentContract.View view, PaymentInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setPayment(int id) {
        view.startLoading();
        interactor.requestPayment(id, new RequestCallback<Payment>() {
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
