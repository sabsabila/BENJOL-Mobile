package com.benjolteam.benjol.presenter;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.UploadReceiptContract;
import com.benjolteam.benjol.interactor.UploadReceiptInteractor;
import com.benjolteam.benjol.model.Payment;

import java.io.File;

public class UploadReceiptPresenter implements UploadReceiptContract.Presenter {
    private UploadReceiptContract.View view;
    private UploadReceiptInteractor interactor;

    public UploadReceiptPresenter(UploadReceiptContract.View view, UploadReceiptInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setReceipt(int id) {
        view.startLoading();
        interactor.requestReceipt(id, new RequestCallback<Payment>() {
            @Override
            public void requestSuccess(Payment data) {
                view.setReceipt(data);
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
    public void saveReceipt(int id, File file) {
        view.startLoading();
        interactor.uploadImage(id, file, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.uploadSuccess(message);
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
