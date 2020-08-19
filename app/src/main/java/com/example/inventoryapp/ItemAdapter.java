package com.example.inventoryapp;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> mItems = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public void setItems(List<Item> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    ItemAdapter(Context context) {
        this.context = context;
    }

    public Item getItemAt(int position) {
        return mItems.get(position);
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item currentItem = mItems.get(position);
        holder.name.setText(currentItem.getName());
        holder.price.setText(currentItem.getPrice().toString());
        holder.quantity.setText(currentItem.getQuantity().toString());
        holder.supplier.setText(currentItem.getSupplier());
        if(currentItem.getImageUri() != null) {
            Glide
                    .with(context)
                    .load(currentItem.getImageUri())
                    .into(holder.image);
        }
        else {
            Glide
                    .with(context)
                    .load(R.drawable.no_image)
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView quantity;
        TextView supplier;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_TextView);
            price = itemView.findViewById(R.id.price_TextView);
            quantity = itemView.findViewById(R.id.quantity_TextView);
            supplier = itemView.findViewById(R.id.supplier_TextView);
            image = itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mItems.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
