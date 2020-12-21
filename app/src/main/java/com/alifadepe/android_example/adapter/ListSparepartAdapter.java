package com.alifadepe.android_example.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.databinding.ItemMotorcycleBinding;
import com.alifadepe.android_example.databinding.ItemSparepartBinding;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Sparepart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListSparepartAdapter extends RecyclerView.Adapter<ListSparepartAdapter.ViewHolder> {
    private List<Sparepart> spareparts;
    private LayoutInflater layoutInflater;

    public ListSparepartAdapter(List<Sparepart> spareparts, LayoutInflater layoutInflater) {
        this.spareparts = spareparts;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSparepartBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setSparepart(spareparts.get(position));
        holder.binding.tvPrice.setText("Rp. " + String.valueOf(spareparts.get(position).getPrice()));
        if(spareparts.get(position).getPicture() != null){
            holder.binding.imageView.setBackground(null);
            Picasso.get()
                    .load(ApiConstant.BASE_URL + "/" + spareparts.get(position).getPicture())
                    .fit()
                    .into(holder.binding.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if(spareparts != null){
            return spareparts.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemSparepartBinding binding;

        public ViewHolder(@NonNull ItemSparepartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
