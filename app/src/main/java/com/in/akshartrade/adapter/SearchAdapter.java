package com.in.akshartrade.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.akshartrade.Model.SearchModel;
import com.in.akshartrade.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<SearchModel.SearchData> list;
    Context context;
    Click click;

    public interface Click{
        void itemCLick(int position);
        void addWatchListClick(int position);
    }

    public SearchAdapter(List<SearchModel.SearchData> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        SearchModel.SearchData model = list.get(position);

        holder.companyName.setText(model.getName());
        holder.stockCategory.setText(model.getSegment());

        holder.addWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                click.addWatchListClick(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.itemCLick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView companyName,stockCategory;
        ImageView addWatchList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            stockCategory = itemView.findViewById(R.id.stockCategory);
            addWatchList = itemView.findViewById(R.id.addWatchList);
        }
    }
}
