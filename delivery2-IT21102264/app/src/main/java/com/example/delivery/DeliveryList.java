package com.example.delivery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DeliveryList extends ArrayAdapter<Delivery> {
    private Activity context;
    List<Delivery> deliveries;

    public DeliveryList(Activity context, List<Delivery> deliveries) {
        super(context, R.layout.layout_user_list, deliveries);
        this.context = context;
        this.deliveries = deliveries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById
                (R.id.textViewName);
        TextView textvieweaddress = (TextView) listViewItem.findViewById
                (R.id.textviewaddress);
        TextView textviewnumber = (TextView) listViewItem.findViewById
                (R.id.textviewnumber);
        Delivery Delivery = deliveries.get(position);
        textViewName.setText(Delivery.getName());
        textvieweaddress.setText(Delivery.getAddress());
        textviewnumber.setText(Delivery.getConNo());
        return listViewItem;
    }
}