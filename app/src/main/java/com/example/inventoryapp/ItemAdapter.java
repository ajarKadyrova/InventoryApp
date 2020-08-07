package com.example.inventoryapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> mItems = new ArrayList<>();
    private OnItemClickListener listener;

    public void setItems(List<Item> items){
        this.mItems = items;
        notifyDataSetChanged();
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
        holder.image.setImageURI(Uri.parse(currentItem.getImageUri()));
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
                    if(listener != null && position != RecyclerView.NO_POSITION) {
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
