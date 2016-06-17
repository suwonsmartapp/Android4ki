package com.suwonsmartapp.android4ki.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.suwonsmartapp.android4ki.R;

/**
 * Created by junsuk on 16. 6. 17..
 */
public class MyCursorAdapter extends CursorAdapter {


    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_query, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.entryIdTextView = (TextView) convertView.findViewById(R.id.entry_id_text);
        holder.titleTextView = (TextView) convertView.findViewById(R.id.title_text);
        holder.subtitleTextView = (TextView) convertView.findViewById(R.id.subtitle_text);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.entryIdTextView.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID)));
        holder.titleTextView.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE)));
        holder.subtitleTextView.setText(cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE)));
    }

    static class ViewHolder {
        TextView entryIdTextView;
        TextView titleTextView;
        TextView subtitleTextView;
    }

}
