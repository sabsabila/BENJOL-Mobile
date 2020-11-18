package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.LoginResponse;
import com.alifadepe.android_example.api_response.RegisterResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.RegisterContract;
import com.alifadepe.android_example.interactor.RegisterInteractor;
import com.alifadepe.android_example.model.User;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private RegisterInteractor interactor;

    public RegisterPresenter(RegisterContract.View view, RegisterInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(User newUser) {
        view.startLoading();
        interactor.requestRegister(newUser, new RequestCallback<RegisterResponse>() {
            @Override
            public void requestSuccess(RegisterResponse data) {
                view.endLoading();
                view.registerSuccess();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.registerFailed(errorMessage);
            }
        });
    }
}
