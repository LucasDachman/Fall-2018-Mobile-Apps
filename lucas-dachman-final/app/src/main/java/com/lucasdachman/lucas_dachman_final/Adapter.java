package com.lucasdachman.lucas_dachman_final;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements EditRestaurantDialogFragment.OnDataChangedListener {
    private static final String TAG = "Adapter";
    private ArrayList<Restaurant> items;
    private FragmentManager fragmentManager;

    public Adapter(ArrayList<Restaurant> items, FragmentManager fragmentManager) {
        this.items = items;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(items.get(i).name);
        final Adapter instance = this;
        viewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Restaurant r = items.get(viewHolder.getAdapterPosition());
                        EditRestaurantDialogFragment frag = EditRestaurantDialogFragment.newInstance(r.name, r.url, viewHolder.getAdapterPosition());
                        frag.setOnDataChangedListener(instance);
                        frag.show(fragmentManager, TAG);
                        return false;
                    }
                });
                menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        RestaurantStore.getInstance().restaurants.remove(viewHolder.getAdapterPosition());
                        onDataChanged();
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
