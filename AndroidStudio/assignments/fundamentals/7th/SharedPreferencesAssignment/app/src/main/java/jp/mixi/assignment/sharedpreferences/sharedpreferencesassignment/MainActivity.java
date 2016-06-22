package jp.mixi.assignment.sharedpreferences.sharedpreferencesassignment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener{

    public static final String COUNT_PREFS = "CountPrefs";
    public static final String COUNT = "count";
    // カウンター
    private int counter;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(COUNT_PREFS, MODE_PRIVATE);

        findViewById(R.id.countup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                // SharedPreferencesを更新する
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt(COUNT, counter);
                editor.commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // counterの表示
        counter = mSharedPreferences.getInt(COUNT, 0);// 0はデフォ
        TextView countTextView = (TextView) findViewById(R.id.count);
        countTextView.setText(String.valueOf(counter));

        // リスナーの登録
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // リスナーの解除
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(COUNT)) {
            // 変更されたのがCOUNTの値ならば表示を更新する
            int n = sharedPreferences.getInt(s, 0);
            TextView countTextView = (TextView) findViewById(R.id.count);
            countTextView.setText(String.valueOf(n));
        }
    }
}
