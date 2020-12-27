package com.alifadepe.android_example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.databinding.ItemBengkelBinding;
import com.alifadepe.android_example.databinding.ItemBookingBinding;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.BookingData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListBookingAdapter extends RecyclerView.Adapter<ListBookingAdapter.ViewHolder> {
    private List<BookingData> bookings;
    private LayoutInflater layoutInflater;
    private final ListItemClickListener mOnClickListener;
    private Context context;

    public ListBookingAdapter(List<BookingData> bookings, LayoutInflater layoutInflater, ListBookingAdapter.ListItemClickListener onClickListener, Context context) {
        this.bookings = bookings;
        this.layoutInflater = layoutInflater;
        this.mOnClickListener = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookingBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBooking(bookings.get(position));
        setStatus(holder, position);
    }

    @Override
    public int getItemCount() {
        if(bookings != null){
            return bookings.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemBookingBinding binding;

        public ViewHolder(@NonNull ItemBookingBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            this.binding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = bookings.get(getAdapterPosition()).getBooking_id();
            mOnClickListener.onListItemClick(position);
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }
    private void setStatus(@NonNull ViewHolder holder, int idx){
        if(bookings!=null && bookings.size() > 0){
            if(bookings.get(idx).getStatus().equalsIgnoreCase("upcoming")) {
                holder.binding.listBookingStatus.setText("Upcoming");
                holder.binding.listBookingStatus.setTextColor(context.getResources().getColor(R.color.colorSecondary));
            }else if(bookings.get(idx).getStatus().equalsIgnoreCase("ongoing")) {
                holder.binding.listBookingStatus.setText("On Going");
                holder.binding.listBookingStatus.setTextColor(context.getResources().getColor(R.color.ongoing));
            }else if(bookings.get(idx).getStatus().equalsIgnoreCase("finished")) {
                holder.binding.listBookingStatus.setText("Finished");
                holder.binding.listBookingStatus.setTextColor(context.getResources().getColor(R.color.finish));
            }else if(bookings.get(idx).getStatus().equalsIgnoreCase("canceled")) {
                holder.binding.listBookingStatus.setText("Canceled");
                holder.binding.listBookingStatus.setTextColor(context.getResources().getColor(R.color.cancel));
            }
        }
    }
}
