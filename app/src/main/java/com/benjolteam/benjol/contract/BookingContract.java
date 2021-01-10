package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Booking;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.model.Service;

import java.util.List;

public interface BookingContract {
    interface View {
        void startLoading();
        void endLoading();
        void showError(String message);
        void bookingSuccess(String message);
        void bookingFailed(String message);
        void setMotor(List<Motorcycle> motorcycles);
        void setService(List<Service> services);
    }

    interface Presenter {
        void book(Booking newBooking);
        void setMotor();
        void setService();
    }

    interface Interactor {
        void requestBooking(Booking newBooking, RequestCallback<String> requestCallback);
        void requestMotor(final RequestCallback<List<Motorcycle>> requestCallback);
        void requestService(final RequestCallback<List<Service>> requestCallback);
    }
}
