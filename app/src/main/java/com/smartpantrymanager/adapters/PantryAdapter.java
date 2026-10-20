package com.smartpantrymanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartpantrymanager.R;
import com.smartpantrymanager.database.PantryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * PantryAdapter - custom adapter for pantry RecyclerView.
 */
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryItemList = new ArrayList<>();
    private OnPantryItemClickListener clickListener;
    private OnPantryItemLongClickListener longClickListener;

    public interface OnPantryItemClickListener {
        void onPantryItemClick(PantryItem item);
    }

    public interface OnPantryItemLongClickListener {
        void onPantryItemLongClick(PantryItem item);
    }

    public void setOnPantryItemClickListener(OnPantryItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnPantryItemLongClickListener(OnPantryItemLongClickListener longListener) {
        this.longClickListener = longListener;
    }

    public void setPantryItems(List<PantryItem> incomingList) {
        this.pantryItemList = incomingList != null ? incomingList : new ArrayList<>();
        notifyDataSetChanged();
    }

    public List<PantryItem> getPantryItems() { return pantryItemList; }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parentGroup, int viewType) {
        View inflatedRow = LayoutInflater.from(parentGroup.getContext())
                .inflate(R.layout.item_pantry, parentGroup, false);
        return new PantryViewHolder(inflatedRow);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem currentEntry = pantryItemList.get(position);
        holder.bindEntry(currentEntry);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onPantryItemClick(currentEntry);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onPantryItemLongClick(currentEntry);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return pantryItemList.size();
    }

    static class PantryViewHolder extends RecyclerView.ViewHolder {
        TextView nameLabel, amountLabel, expiryLabel;

        PantryViewHolder(@NonNull View rowView) {
            super(rowView);
            nameLabel = rowView.findViewById(R.id.pantry_item_name);
            amountLabel = rowView.findViewById(R.id.pantry_item_quantity);
            expiryLabel = rowView.findViewById(R.id.pantry_item_expiry);
        }

        void bindEntry(PantryItem entry) {
            nameLabel.setText(entry.getName());
            amountLabel.setText(entry.getAmount() + " " + entry.getUnit());

            if (entry.getExpiryDate() != null && !entry.getExpiryDate().isEmpty()) {
                expiryLabel.setVisibility(View.VISIBLE);
                expiryLabel.setText("Expires: " + entry.getExpiryDate());
            } else {
                expiryLabel.setVisibility(View.GONE);
            }
        }
    }
}
