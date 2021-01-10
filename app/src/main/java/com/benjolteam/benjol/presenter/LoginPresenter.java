package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.api_response.LoginResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.LoginContract;
import com.benjolteam.benjol.interactor.LoginInteractor;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String email, String password) {
        view.startLoading();
        interactor.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.endLoading();
                view.loginSuccess();
                interactor.saveToken(data.token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.loginFailed(errorMessage);
            }
        });
    }

    @Override
    public void register() {
        view.register();
    }
}
