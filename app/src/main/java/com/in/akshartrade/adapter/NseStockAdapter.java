package com.in.akshartrade.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        WatchListModel.WatchListData model = list.get(position);


        holder.companyName.setText(model.getName());
        holder.stockCategory.setText(model.getExchange());
        holder.stockPrice.setText(model.getChart_data().getLast_price());


//        List<PointValue> values = new ArrayList<PointValue>();
//        values.add(new PointValue(0, 2));
//        values.add(new PointValue(1, 4));
//        values.add(new PointValue(2, 3));
//        values.add(new PointValue(3, 4));
//
//        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
//        List<Line> lines = new ArrayList<Line>();
//        lines.add(line);
//
//        LineChartData data = new LineChartData();
//        data.setLines(lines);



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

        LineChartView lineChart = new LineChartView(context);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            stockCategory = itemView.findViewById(R.id.stockCategory);
            stockPrice = itemView.findViewById(R.id.stockPrice);
            livePrice = itemView.findViewById(R.id.livePrice);
            livePercentage = itemView.findViewById(R.id.livePercentage);
            lineChart = itemView.findViewById(R.id.lineChart);
        }
    }
}
