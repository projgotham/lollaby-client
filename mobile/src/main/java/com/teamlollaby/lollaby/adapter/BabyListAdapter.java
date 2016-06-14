package com.teamlollaby.lollaby.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamlollaby.lollaby.R;
import com.teamlollaby.lollaby.data.BabyListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinGu on 2015-10-31.
 */
public class BabyListAdapter extends RecyclerView.Adapter<BabyListAdapter.CustomViewHolder> {
    private static final String TAG = "BabyListAdapter";
    private static final int PRESSURE_THRESHOLD = 500;
    private static final int NOISE_THRESHOLD = 1;
    private List<BabyListData> babyListDatas;
    private Context context;
    private View recyclerView;
    private ImageView baby_status;

    public BabyListAdapter(Context context, List<BabyListData> babyListDatas) {
        this.babyListDatas = babyListDatas;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_baby_card, null);
        baby_status = (ImageView) view.findViewById(R.id.baby_status_image);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        BabyListData babyListData = babyListDatas.get(position);
        holder.name.setText(babyListData.babyName);
        holder.temperature.setText("온도 : ".concat(String.valueOf(babyListData.temperature)).concat("°C"));
        holder.humidity.setText("습도 : ".concat(String.valueOf(babyListData.humidity)).concat("%"));

        if(babyListData.noise > NOISE_THRESHOLD){
            holder.noise.setText("소음 : 비정상");
            if (babyListData.pressure > PRESSURE_THRESHOLD){
                holder.pressure.setText("압력 : 비정상");
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.emergency_red));
            }
            else{
                holder.pressure.setText("압력 : 정상");
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.noise_yellow));
            }
            Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.baby_sad);
            baby_status.setImageBitmap(image);
        }
        else{
            holder.noise.setText("소음 : 정상");
            if(babyListData.pressure > PRESSURE_THRESHOLD){
                holder.pressure.setText("압력 : 비정상");
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.emergency_red));
                Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.baby_sad);
                baby_status.setImageBitmap(image);
            }
            else{
                holder.pressure.setText("압력 : 정상");
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.teal400));
                Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.baby_happy);
                baby_status.setImageBitmap(image);
            }
        }
    }

    @Override
    public int getItemCount() {
        return babyListDatas == null ? 0 : babyListDatas.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView temperature;
        protected TextView humidity;
        protected TextView pressure;
        protected TextView noise;

        public CustomViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.baby_name);
            this.temperature = (TextView) itemView.findViewById(R.id.baby_temperature);
            this.humidity = (TextView) itemView.findViewById(R.id.baby_humidity);
            this.pressure = (TextView) itemView.findViewById(R.id.baby_pressure);
            this.noise = (TextView) itemView.findViewById(R.id.baby_noise);

        }

    }
}
