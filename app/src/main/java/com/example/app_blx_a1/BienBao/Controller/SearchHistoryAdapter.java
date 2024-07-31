package com.example.app_blx_a1.BienBao.Controller;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.example.app_blx_a1.R;
public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {
    private List<String> searchHistory;
    private List<String> filteredSearchHistory;
    private OnItemClickLister listener;

    public interface OnItemClickLister{
        void onItemHistoryClick(String query);
    }

    public SearchHistoryAdapter(List<String> searchHistory, OnItemClickLister listener){
        this.searchHistory = searchHistory;
        this.filteredSearchHistory = new ArrayList<>(searchHistory);
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView queryTextView;
        ViewHolder(View itemView){
            super(itemView);
            queryTextView = itemView.findViewById(R.id.queryTextView);
        }

    }

    @NonNull
    @Override
    public SearchHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_search_history_bien_bao,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryAdapter.ViewHolder holder, int position) {
        String query = filteredSearchHistory.get(position);
        holder.queryTextView.setText(query);
        holder.itemView.setOnClickListener(v -> listener.onItemHistoryClick(query));
    }

    @Override
    public int getItemCount() {
        return filteredSearchHistory.size();
    }

    public void filter(String text){
        filteredSearchHistory.clear();
        if(text.isEmpty()){
            filteredSearchHistory.addAll(searchHistory);
        }else {
            for(String item : searchHistory){
                if(item.toLowerCase().contains(text.toLowerCase())){
                    filteredSearchHistory.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void clearFilter() {
        filteredSearchHistory.clear();
        filteredSearchHistory.addAll(searchHistory);
        notifyDataSetChanged();
    }

}
