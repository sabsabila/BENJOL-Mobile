package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.interactor.DashboardInteractor;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.model.User;

import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter {
    private DashboardContract.View view;
    private DashboardInteractor interactor;

    public DashboardPresenter(DashboardContract.View view, DashboardInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFirstName() {
        interactor.getUser(new RequestCallback<List<Profile>>() {

            @Override
            public void requestSuccess(List<Profile> response) {
                view.setFirstName(response.get(0).getFirst_name());
            }

            @Override
            public void requestFailed(String errorMessage) {
               view.showError(errorMessage);
            }
        });
    }

}
