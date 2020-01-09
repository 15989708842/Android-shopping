package com.example.administrator.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopping.R;
import com.example.administrator.shopping.bean.ShopBean;

import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<ShopBean> list;
    public ShopAdapter(Context context, List<ShopBean> list){
        this.layoutInflater=LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public  int getCount(){
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position){
        return  position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.shop_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        ShopBean ShoppingInfo = (ShopBean) getItem(position);
        viewHolder.tvShoppingTitle.setText(ShoppingInfo.getShoppingTitle());
        viewHolder.tvShoppingPrice.setText(ShoppingInfo.getShoppingPrice());
        viewHolder.ivImg.setBackgroundResource(ShoppingInfo.getImg());
        return convertView;
    }
    class ViewHolder{
        TextView tvShoppingTitle;
        TextView tvShoppingPrice;
        ImageView ivImg;
        public ViewHolder(View view){
            tvShoppingTitle=(TextView) view.findViewById(R.id.item_title);
            tvShoppingPrice=(TextView) view.findViewById(R.id.item_price);
            ivImg = view.findViewById(R.id.iv_img);
        }
    }
}
