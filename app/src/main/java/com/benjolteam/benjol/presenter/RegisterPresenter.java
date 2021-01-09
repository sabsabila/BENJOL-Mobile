package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.api_response.RegisterResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.RegisterContract;
import com.benjolteam.benjol.interactor.RegisterInteractor;
import com.benjolteam.benjol.model.User;

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
