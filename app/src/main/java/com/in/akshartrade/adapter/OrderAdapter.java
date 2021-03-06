package com.in.akshartrade.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<OrderModel.OrderData> list;
    Context context;
    Click click;

    public interface Click {
        void onItemClick(int position);
    }

    public OrderAdapter(List<OrderModel.OrderData> list, Context context, Click click) {
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

        OrderModel.OrderData model = list.get(position);

        String orderType = model.getOrder_type();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onItemClick(position);
            }
        });

        if (orderType.equals("Buy")){
            holder.orderType.setText(model.getOrder_type());
            holder.orderType.setTextColor(Color.parseColor("#58B182"));
        }
        if (orderType.equals("Sell")){

            holder.orderType.setText(model.getOrder_type());
            holder.orderType.setTextColor(Color.parseColor("#DC4F50"));
        }

        holder.companyName.setText(model.getName());
        holder.stockCategory.setText(model.getExchange() + "  QTY "+model.getQTY());
        holder.ltpPrice.setText("??? "+model.getLTP());
        holder.plPrice.setText("??? "+model.getpAndL());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, ltpPrice, plPrice, stockCategory,orderType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            ltpPrice = itemView.findViewById(R.id.ltpPrice);
            plPrice = itemView.findViewById(R.id.plPrice);
            stockCategory = itemView.findViewById(R.id.stockCategory);
            orderType = itemView.findViewById(R.id.orderType);
        }
    }
}
