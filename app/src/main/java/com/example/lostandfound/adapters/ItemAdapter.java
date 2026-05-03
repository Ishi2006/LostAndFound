package com.example.lostandfound.adapters;

import android.graphics.Color;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lostandfound.R;
import com.example.lostandfound.models.Item;
import java.text.SimpleDateFormat;
import java.util.*;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public interface OnItemClickListener { void onClick(Item item); }

    private List<Item> items;
    private final OnItemClickListener listener;

    public ItemAdapter(List<Item> items, OnItemClickListener listener) {
        this.items    = items;
        this.listener = listener;
    }

    public void updateList(List<Item> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvCategory.setText(item.getCategory());
        holder.tvLocation.setText(item.getLocation());
        holder.tvDate.setText(new SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                .format(new Date(item.getTimestamp())));

        holder.tvType.setText(item.getType().toUpperCase());
        holder.tvType.setBackgroundColor(
                item.getType().equalsIgnoreCase("lost")
                        ? Color.parseColor("#FFCDD2")
                        : Color.parseColor("#C8E6C9"));
        holder.tvType.setTextColor(
                item.getType().equalsIgnoreCase("lost")
                        ? Color.parseColor("#B71C1C")
                        : Color.parseColor("#1B5E20"));

        holder.itemView.setOnClickListener(v -> listener.onClick(item));
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCategory, tvLocation, tvDate, tvType;
        ItemViewHolder(View v) {
            super(v);
            tvTitle    = v.findViewById(R.id.tv_title);
            tvCategory = v.findViewById(R.id.tv_category);
            tvLocation = v.findViewById(R.id.tv_location);
            tvDate     = v.findViewById(R.id.tv_date);
            tvType     = v.findViewById(R.id.tv_type);
        }
    }
}