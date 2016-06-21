package jp.mixi.assignment.sharedpreferences.sharedpreferencesassignment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    // カウンター
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: カウントアップ機能を作る
        findViewById(R.id.countup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                TextView countTextView = (TextView) findViewById(R.id.count);
                countTextView.setText(String.valueOf(counter));
            }
        });
        //TODO: カウントを保存する
        //TODO: onSharedPreferenceChangedから変更する
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
