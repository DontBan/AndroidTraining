
package jp.mixi.assignment.listview.beg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BookActivity extends Activity {

    @SuppressWarnings("unused")
    private static final String TAG = BookActivity.class.getSimpleName();

    public static final String BOOK_TITLE = "BookTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);

        // TODO:MainActiviyから送られてきたtitleを表示してください。
        Intent received = getIntent();
        String title = received.getStringExtra(BOOK_TITLE);
        TextView titleTextView = (TextView) findViewById(R.id.BookTitle);
        titleTextView.setText(title);
    }
}
