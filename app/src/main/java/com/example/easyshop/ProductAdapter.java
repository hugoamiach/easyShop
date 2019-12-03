package com.example.easyshop;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private List<Produit> mProductList;
    private LayoutInflater mInflater;

    public ProductAdapter(List<Produit> list, LayoutInflater inflater) {
        mProductList = list;
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewItem item;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_product, null);
            item = new ViewItem();
            item.image = (ImageView) convertView.findViewById(R.id.image);
            item.title = (TextView) convertView.findViewById(R.id.title);
            item.price = (TextView) convertView.findViewById(R.id.price);
            item.description = (TextView) convertView.findViewById(R.id.desc);

            convertView.setTag(item);
        } else {
            item = (ViewItem) convertView.getTag();
        }

        Produit curProduct = mProductList.get(position);

        item.title.setText(curProduct.title);
        item.price.setText("Price " + curProduct.price);
        item.description.setText( curProduct.description);

        if (!curProduct.image.equals("")) {
            AQuery aq = new AQuery(item.image);
            aq.id(item.image).image(curProduct.image);
        } else {
            item.image.setImageResource(R.drawable.ic_launcher_background);
        }

        return convertView;
    }

    private class ViewItem {
        ImageView image;
        TextView title;
        TextView price;
        TextView description;
    }



}