package com.in.akshartrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.akshartrade.Model.OptionalChainModel;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.util.List;

public class OptionalChainAdapter extends RecyclerView.Adapter<OptionalChainAdapter.ViewHolder> {

    List<OptionalChainModel> list;
    Context context;
    Click click;

    public interface Click {
        void onItmClick(int position);
    }

    public OptionalChainAdapter(List<OptionalChainModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.optional_chain_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OptionalChainModel model  = list.get(position);

        holder.percentage.setText(model.getPercentage());
        holder.rupeeInLakh.setText(model.getRupeeInLakh());
        holder.rupeePercentage.setText(model.getRupeePercentage());
        holder.ltpOne.setText(model.getLtpOne());
        holder.ltpSecond.setText(model.getLtpSecond());
        holder.strikePrice.setText(model.getStrikePrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView percentage, rupeeInLakh, rupeePercentage, ltpOne, ltpSecond, strikePrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            percentage = itemView.findViewById(R.id.percentage);
            rupeeInLakh = itemView.findViewById(R.id.rupeeInLakh);
            rupeePercentage = itemView.findViewById(R.id.rupeePercentage);
            ltpOne = itemView.findViewById(R.id.ltpOne);
            ltpSecond = itemView.findViewById(R.id.ltpSecond);
            strikePrice = itemView.findViewById(R.id.strikePrice);


        }
    }
}
