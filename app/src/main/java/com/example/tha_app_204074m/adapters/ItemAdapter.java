package com.example.tha_app_204074m.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tha_app_204074m.R;
import com.example.tha_app_204074m.activities.ItemInfoForm;
import com.example.tha_app_204074m.data.entity.ItemInfo;

public class ItemAdapter extends ListAdapter<ItemInfo,ItemAdapter.ViewHolder> {
    private Activity activity;

    public ItemAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    private static final DiffUtil.ItemCallback<ItemInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<ItemInfo>() {
        @Override
        public boolean areItemsTheSame(ItemInfo oldItem, ItemInfo newItem) {
            return oldItem.getItemId() == newItem.getItemId();
        }

        @Override
        public boolean areContentsTheSame(ItemInfo oldItem, ItemInfo newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && (oldItem.getDescription().equals(newItem.getDescription())
                    && (oldItem.getPrice() == newItem.getPrice()));
        }
    };


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ItemInfo itemInfo = getItemAt(position);

        TextView tv_title = holder.tv_title;
        tv_title.setText(itemInfo.getName());

        TextView tv_description = holder.tv_description;
        tv_description.setText(itemInfo.getDescription());

        TextView tv_price = holder.tv_price;
        tv_price.setText("Rs. "+String.valueOf(itemInfo.getPrice()));

        ImageView iv_itemImage = holder.iv_item_image;
        int imagePoint = position%3;
        iv_itemImage.setImageResource(imagePoint==1?R.drawable.cake:imagePoint==2?R.drawable.cake2:R.drawable.cake3);

        holder.btn_edit.setOnClickListener(v -> {
            Intent intent = new Intent(this.activity, ItemInfoForm.class);
            intent.putExtra("Title",itemInfo.getName());
            intent.putExtra("Description",itemInfo.getDescription());
            intent.putExtra("Price",itemInfo.getPrice());
            intent.putExtra("Position",position);
            intent.putExtra("ID",itemInfo.getItemId());
            activity.startActivityForResult(intent,2);
        });
    }

    public ItemInfo getItemAt(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_item_image;
        private TextView tv_title;
        private TextView tv_description;
        private TextView tv_price;
        private Button btn_edit;


        public ViewHolder(View itemView){
            super(itemView);
            iv_item_image = itemView.findViewById(R.id.iv_item_image);
            tv_title =  itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_price = itemView.findViewById(R.id.tv_price);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }


}
