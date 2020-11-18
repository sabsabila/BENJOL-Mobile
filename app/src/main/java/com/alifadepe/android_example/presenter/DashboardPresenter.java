package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.interactor.DashboardInteractor;

public class DashboardPresenter implements DashboardContract.Presenter {
    private DashboardContract.View view;
    private DashboardInteractor interactor;

    public DashboardPresenter(DashboardContract.View view, DashboardInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFirstName() {
        view.startLoading();
        interactor.getUser(new RequestCallback<UserResponse>() {

            @Override
            public void requestSuccess(UserResponse data) {
                view.setFirstName(data.first_name);
            }

            @Override
            public void requestFailed(String errorMessage) {
               view.showError(errorMessage);
            }
        });
    }

}
