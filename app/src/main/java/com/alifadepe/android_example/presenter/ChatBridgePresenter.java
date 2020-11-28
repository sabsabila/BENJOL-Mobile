package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ChatBridgeContract;
import com.alifadepe.android_example.interactor.ChatBridgeInteractor;
import com.alifadepe.android_example.model.Bengkel;

import java.util.List;

public class ChatBridgePresenter implements ChatBridgeContract.presenter {
    private ChatBridgeContract.View view;
    private ChatBridgeInteractor interactor;

    public ChatBridgePresenter(ChatBridgeContract.View view, ChatBridgeInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setBengkel() {
        interactor.requestBengkel(new RequestCallback<List<Bengkel>>() {
            @Override
            public void requestSuccess(List<Bengkel> data) {
                view.setBengkel(data.get(0));
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

}
