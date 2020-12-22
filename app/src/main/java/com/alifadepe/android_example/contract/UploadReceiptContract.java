package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Payment;
import com.alifadepe.android_example.model.Profile;

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
