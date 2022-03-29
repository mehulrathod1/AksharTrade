package com.in.akshartrade.Adapter;

import android.content.Context;
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

        double LTP = Double.parseDouble(model.getLTP());
        double PL = Double.parseDouble(model.getpAndL());

        holder.companyName.setText(model.getName());
        holder.stockCategory.setText(model.getExchange() + "  QTY "+model.getQTY());
        holder.ltpPrice.setText(new DecimalFormat("##.##").format(LTP));
//        holder.plPrice.setText(new DecimalFormat("##.##").format(PL));

        holder.plPrice.setText(model.getpAndL());

        Log.e("PL", "onBindViewHolder: "+PL );
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
