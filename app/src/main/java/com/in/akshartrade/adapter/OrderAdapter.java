package com.in.akshartrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<OrderModel> list;
    Context context;
    Click click;

    public interface Click {
        void onItemClick(int position);
    }

    public OrderAdapter(List<OrderModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderModel model = list.get(position);

        holder.companyName.setText(model.getCompanyName());
        holder.stockCategory.setText(model.getStockCategory());
        holder.ltpPrice.setText(model.getLtpPrice());
        holder.plPrice.setText(model.getPlPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, ltpPrice, plPrice, stockCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            ltpPrice = itemView.findViewById(R.id.ltpPrice);
            plPrice = itemView.findViewById(R.id.plPrice);
            stockCategory = itemView.findViewById(R.id.stockCategory);

        }
    }
}
