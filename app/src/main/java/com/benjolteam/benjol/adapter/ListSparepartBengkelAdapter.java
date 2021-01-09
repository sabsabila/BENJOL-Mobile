package com.benjolteam.benjol.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.android_example.databinding.ItemSparepartBengkelBinding;
import com.benjolteam.benjol.model.Sparepart;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ListSparepartBengkelAdapter extends RecyclerView.Adapter<ListSparepartBengkelAdapter.ViewHolder> {
    private List<Sparepart> spareparts;
    private LayoutInflater layoutInflater;

    public ListSparepartBengkelAdapter(List<Sparepart> spareparts, LayoutInflater layoutInflater) {
        this.spareparts = spareparts;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSparepartBengkelBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setSparepart(spareparts.get(position));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.binding.tvPrice.setText("Rp. " + formatRupiah.format(spareparts.get(position).getPrice()).substring(2));
        holder.binding.tvStock.setText("Stock : " + String.valueOf(spareparts.get(position).getStock()));
        if(spareparts.get(position).getPicture() != null){
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
        ItemSparepartBengkelBinding binding;

        public ViewHolder(@NonNull ItemSparepartBengkelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
