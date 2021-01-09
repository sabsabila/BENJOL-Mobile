package com.benjolteam.benjol.contract;

public interface MainContract {
    interface View {
        void whenUserLogin();
        void whenUserNotLogin();
    }

    interface Presenter {
        void checkIsUserLogin();
    }

    interface Interactor {
        boolean isUserLogin();
    }
}
