package com.teamlollaby.lollaby.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamlollaby.lollaby.R;
import com.teamlollaby.lollaby.data.BabyListData;

import java.util.List;

/**
 * Created by MinGu on 2015-11-01.
 */
public class BabyListAdapter extends WearableListView.Adapter {
    private Context context;
    private List<BabyListData> babyListDatas;
    private LayoutInflater layoutInflater;
    private BabyListData babyListData;
    private static final int NOISE_THRESHOLD = 1;
    private static final int PRESSURE_THRESHOLD = 500;

    public BabyListAdapter(Context context, List<BabyListData> babyListDatas) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.babyListDatas = babyListDatas;
    }

    public static class ItemViewHolder extends WearableListView.ViewHolder{
        private TextView name;


        public ItemViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.baby_name);
        }
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(layoutInflater.inflate(R.layout.layout_baby_card, null));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        TextView name = itemHolder.name;

        babyListData = babyListDatas.get(position);
        name.setText(babyListData.babyName);

        if(babyListData.noise > NOISE_THRESHOLD){
            if (babyListData.pressure > PRESSURE_THRESHOLD){
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.emergency_red));
            }
            else{
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.noise_yellow));
            }
        }
        else{
            if(babyListData.pressure > PRESSURE_THRESHOLD){
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.emergency_red));

            }
            else{
                holder.itemView.findViewById(R.id.background_layout).setBackgroundColor(context.getResources().getColor(R.color.teal400));
            }
        }

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return babyListDatas.size();
    }
}

