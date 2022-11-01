package com.example.delivery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProfileList extends ArrayAdapter<Profile> {
    private Activity context;
    List<Profile> profiles;

    public ProfileList(Activity context, List<Profile> profiles) {
        super(context, R.layout.layout_user_list, profiles);
        this.context = context;
        this.profiles = profiles;
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
        Profile Profile = profiles.get(position);
        textViewName.setText(Profile.getName());
        textvieweaddress.setText(Profile.getEmail());
        textviewnumber.setText(Profile.getConNo());
        return listViewItem;
    }
}