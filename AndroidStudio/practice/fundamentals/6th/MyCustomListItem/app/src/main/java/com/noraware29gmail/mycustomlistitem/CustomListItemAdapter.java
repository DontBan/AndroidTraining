package com.noraware29gmail.mycustomlistitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tomohiro on 16/05/18.
 */
public class CustomListItemAdapter extends ArrayAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public CustomListItemAdapter(Context context, List<String> objects) {
        super(context, 0, objects);

        mLayoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder;
        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.custom_list_item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.TitleText);
            holder.subTitle = (TextView) view.findViewById(R.id.SubTitleText);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        String item = getItem(position);
        holder.title.setText("Title:" + item);
        holder.subTitle.setText("SubTitle" + item);

        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView subTitle;
    }
}
