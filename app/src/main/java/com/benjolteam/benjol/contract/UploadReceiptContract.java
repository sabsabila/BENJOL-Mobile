package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Payment;

import java.io.File;

public interface UploadReceiptContract {
    interface View {
        void setReceipt(Payment payment);
        void showError(String message);
        void uploadSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setReceipt(int id);
        void saveReceipt(int id, File file);
    }

    interface Interactor {
        void requestReceipt(int id, RequestCallback<Payment> requestCallback);
        void uploadImage(int id, File file, RequestCallback<String> requestCallback);
    }
}
