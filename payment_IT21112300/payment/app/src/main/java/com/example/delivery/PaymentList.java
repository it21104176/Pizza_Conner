package com.example.delivery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PaymentList extends ArrayAdapter<Payment> {
    private Activity context;
    List<Payment> payments;

    public PaymentList(Activity context, List<Payment> payments) {
        super(context, R.layout.layout_user_list, payments);
        this.context = context;
        this.payments = payments;
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
        Payment Payment = payments.get(position);
        textViewName.setText(Payment.getCardNo());
        textvieweaddress.setText(Payment.getCardName());
        textviewnumber.setText(Payment.getAmount());
        return listViewItem;
    }
}