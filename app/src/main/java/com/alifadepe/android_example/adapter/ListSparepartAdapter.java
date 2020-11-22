package com.alifadepe.android_example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.R;
import com.alifadepe.android_example.databinding.ItemSparepartBinding;
import com.alifadepe.android_example.model.Sparepart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListSparepartAdapter extends RecyclerView.Adapter<ListSparepartAdapter.SparepartViewHolder> {
    private List<Sparepart> spareparts;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListSparepartAdapter(List<Sparepart> spareparts, LayoutInflater layoutInflater, Context context) {
        this.spareparts = spareparts;
        this.layoutInflater = layoutInflater;
        this.context = context;

    }

    @NonNull
    @Override
    public SparepartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(ItemSparepartBinding.inflate(layoutInflater));
        layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSparepartBinding binding = DataBindingUtil.inflate(layoutInflater.from(parent.getContext()), R.layout.item_sparepart,parent,false);
        return new SparepartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SparepartViewHolder holder, int position) {
        String image = spareparts.get(position).getPicture();
        holder.binding.setSparepart(spareparts.get(position));
        holder.binding.tvBengkel.setText(spareparts.get(position).getBengkelId());
        holder.binding.tvBengkelAddress.setText(spareparts.get(position).getBengkelId());
        holder.binding.tvSparepartName.setText(spareparts.get(position).getName());
        holder.binding.tvPrice.setText(spareparts.get(position).getPrice());

        Picasso.with(context).load(image).fit().centerInside().into(holder.binding.imageView);

//        holder.binding.imageView.setImageDrawable(spareparts.get(position).getPicture());
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

    class SparepartViewHolder extends RecyclerView.ViewHolder {
        ItemSparepartBinding binding;

        public SparepartViewHolder(ItemSparepartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
