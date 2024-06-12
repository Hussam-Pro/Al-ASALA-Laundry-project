package com.app.alasala.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alasala.R;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private LayoutInflater mInflater;
    private List<RequestModel> requestModelList = new ArrayList<>();;

    // data is passed into the constructor
    public RequestAdapter(Context context, List<RequestModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.requestModelList = data;
    }
    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_order_history, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        RequestModel model = requestModelList.get(position);
        holder.tvServiceName.setText(model.getServiceName());
        holder.tvQuantity.setText(String.valueOf(model.getQuantity()));
        holder.tvPrice.setText(String.valueOf(model.getBasePrice()));
        holder.tvTotalPrice.setText(String.valueOf(model.getTotalPrice()));
        holder.tvName.setText(model.getName());
        holder.tvPhone.setText(model.getPhone());
        holder.tvCity.setText(model.getCity());
        holder.tvHome.setText(model.getHome());
    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        private View view;
        TextView tvServiceName;
        TextView tvQuantity;
        TextView tvPrice;
        TextView tvTotalPrice;
        TextView tvName;
        TextView tvCity;
        TextView tvPhone;
        TextView tvHome;

        RequestViewHolder(View itemView) {
            super(itemView);
            view = itemView;
//            itemView.setOnClickListener(this);

            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvHome = itemView.findViewById(R.id.tvHome);
        }

    }

}
