package com.alifadepe.android_example.adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.databinding.ItemBookingBinding;
import com.alifadepe.android_example.databinding.ItemPickupBinding;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.PickupData;

import java.util.Calendar;
import java.util.List;

public class ListPickupAdapter extends RecyclerView.Adapter<ListPickupAdapter.ViewHolder> {
    private List<PickupData> pickups;
    private LayoutInflater layoutInflater;
    private final ListItemClickListener mOnClickListener;

    public ListPickupAdapter(List<PickupData> pickups, LayoutInflater layoutInflater, ListPickupAdapter.ListItemClickListener onClickListener) {
        this.pickups = pickups;
        this.layoutInflater = layoutInflater;
        this.mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPickupBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setPickup(pickups.get(position));
        holder.binding.listPickupLocatiom.setText("Pickup Location : " + pickups.get(position).getPickup_location());
        holder.binding.listDropoffLocation.setText("Drop Off Location : " + pickups.get(position).getDropoff_location());
        String[] date = pickups.get(position).getRepairment_date().split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        CharSequence dateFormatted = DateFormat.format("EEE, d MMM yyyy", calendar);
        holder.binding.listPickupDate.setText(dateFormatted);
    }

    @Override
    public int getItemCount() {
        if(pickups != null){
            return pickups.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemPickupBinding binding;

        public ViewHolder(@NonNull ItemPickupBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            this.binding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = pickups.get(getAdapterPosition()).getBooking_id();
            mOnClickListener.onListItemClick(position);
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }
}
