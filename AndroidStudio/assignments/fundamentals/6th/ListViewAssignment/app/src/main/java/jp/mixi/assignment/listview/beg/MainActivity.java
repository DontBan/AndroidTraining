
package jp.mixi.assignment.listview.beg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Activity mActivity;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        // データの作成
        ArrayList<Book> list = new ArrayList<Book>();
        for (int i = 0; i < 20; i++) {
            list.add(new Book("タイトル" + i, "出版社" + i, i * 10));
        }

        // (リストアイテムのレイアウトは用意されているlist_item_book.xmlをしてください。)
        BookArrayAdapter bookArrayAdapter = new BookArrayAdapter(mActivity,
                list);
        mListView = (ListView) findViewById(R.id.BookList);

        mListView.setAdapter(bookArrayAdapter);
        // (BookActivityは用意されているものを使用してください)
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = (Book) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra(BookActivity.BOOK_TITLE, book.getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerForContextMenu(mListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        // ContextMenuを設定
        getMenuInflater().inflate(R.menu.context_menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() != R.id.DeleteListItem && item.getItemId() != R.id.AddListItem) {
            return false;
        }

        // MenuItemからContextMenuInfoを取得し、AdapterContextMenuInfoにキャストします
        ContextMenuInfo menuInfo = item.getMenuInfo();
        AdapterContextMenuInfo adapterInfo = (AdapterContextMenuInfo) menuInfo;

        // AdapterContextMenuInfoから長押ししたリストアイテムのpositionを取得します
        int position = adapterInfo.position;

        // ListViewから長押しされたリストアイテムを取得します
        Book book = (Book) mListView.getItemAtPosition(position);
        // ListViewからセットされているAdapterを取得します
        BookArrayAdapter adapter = (BookArrayAdapter) mListView.getAdapter();

        if (item.getItemId() == R.id.DeleteListItem) {
            adapter.remove(book);
        } else if (item.getItemId() == R.id.AddListItem) {
            adapter.add(book);
        }
        adapter.notifyDataSetChanged();
        
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterForContextMenu(mListView);
    }

}
