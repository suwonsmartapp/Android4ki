package com.suwonsmartapp.android4ki.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suwonsmartapp.android4ki.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by junsuk on 16. 5. 31..
 */
public class ContactAdapter extends BaseAdapter {

    private List<Contact> mData;

    public ContactAdapter(List<Contact> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, parent, false);
            holder = new ViewHolder();
            holder.circleImageView = (CircleImageView) convertView.findViewById(R.id.image_view);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = (Contact) getItem(position);

        holder.circleImageView.setImageResource(R.drawable.profile);
        holder.nameTextView.setText(contact.getName());

        return convertView;
    }

    static class ViewHolder {
        CircleImageView circleImageView;
        TextView nameTextView;
    }
}
