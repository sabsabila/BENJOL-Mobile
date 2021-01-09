package com.benjolteam.benjol.contract;

public interface SplashContract {
    interface View {
        void startApp();
        void showAboutUs();
    }

    interface Presenter {
        void redirectToApp();
        void redirectToAboutUs();
    }
}
