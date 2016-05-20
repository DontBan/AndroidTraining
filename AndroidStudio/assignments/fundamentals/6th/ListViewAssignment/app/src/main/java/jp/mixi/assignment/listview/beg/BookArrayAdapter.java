package jp.mixi.assignment.listview.beg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tomohiro on 16/05/20.
 */
public class BookArrayAdapter extends ArrayAdapter<Book> {
    private static final String TAG = BookArrayAdapter.class.getSimpleName();

    private LayoutInflater mLayoutInflater;

    public BookArrayAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_book, parent, false);
        } else {
            view = convertView;
        }

        Book book = getItem(position);
        TextView title = (TextView) view.findViewById(R.id.Title);
        title.setText(book.getTitle());
        TextView publisher = (TextView) view.findViewById((R.id.Publisher));
        publisher.setText(book.getPublisher());
        TextView price = (TextView) view.findViewById(R.id.Price);
        price.setText(String.valueOf(book.getPrice()));

        return view;
    }
}
