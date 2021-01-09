package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.BookingData;

import java.util.List;

public interface ListBookingContract {
    interface View {
        void showError(String message);
        void setBooking(List<BookingData> bookings);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setBooking();
    }

    interface Interactor {
        void requestBookings(final RequestCallback<List<BookingData>> requestCallback);
    }
}
