package com.in.akshartrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.akshartrade.Model.WatchListModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Model.NseStockModel;

import java.text.DecimalFormat;
import java.util.List;

public class NseStockAdapter extends RecyclerView.Adapter<NseStockAdapter.ViewHolder> {

    List<WatchListModel.WatchListData> list;
    Context context;
    Click click;


    public interface Click {
        public void onItemClick(int position);
    }

    public NseStockAdapter(List<WatchListModel.WatchListData> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_data_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WatchListModel.WatchListData model = list.get(position);

        double lastPriceValue = Double.parseDouble(model.getChart_data().getLast_price());



        holder.companyName.setText(model.getName());
        holder.stockCategory.setText(model.getExchange());
        holder.stockPrice.setText(new DecimalFormat("##.##").format(lastPriceValue));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                click.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, stockCategory, stockPrice, livePrice, livePercentage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            stockCategory = itemView.findViewById(R.id.stockCategory);
            stockPrice = itemView.findViewById(R.id.stockPrice);
            livePrice = itemView.findViewById(R.id.livePrice);
            livePercentage = itemView.findViewById(R.id.livePercentage);

        }
    }
}
