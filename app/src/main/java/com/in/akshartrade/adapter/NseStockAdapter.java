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
        holder.stockPrice.setText("₹ "+model.getChart_data().getLast_price());
        holder.profitAndLoss.setText(model.getChart_data().getProfit_and_lost());
        holder.percentage.setText("("+model.getChart_data().getPercentage_val()+")");


        holder.lineChart.setInteractive(true);
//        holder.lineChart.setZoomType(ZoomType.HORIZONTAL);


        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 497));
        values.add(new PointValue(1, 494));
        values.add(new PointValue(2, 490));
        values.add(new PointValue(3, 488));
        values.add(new PointValue(4, 300));
        values.add(new PointValue(5, 392));
        values.add(new PointValue(6, 491));
        values.add(new PointValue(7, 490));


        Line line = new Line(values);
        line.setFilled(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        holder.lineChart.setLineChartData(data);

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

        TextView companyName, stockCategory, stockPrice, profitAndLoss, percentage;

        LineChartView lineChart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            stockCategory = itemView.findViewById(R.id.stockCategory);
            stockPrice = itemView.findViewById(R.id.stockPrice);
            profitAndLoss = itemView.findViewById(R.id.profitAndLoss);
            percentage = itemView.findViewById(R.id.percentage);
            lineChart = itemView.findViewById(R.id.lineChart);
        }
    }
}
