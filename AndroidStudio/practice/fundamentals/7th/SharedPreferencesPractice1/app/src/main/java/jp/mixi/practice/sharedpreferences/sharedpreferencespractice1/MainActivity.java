package jp.mixi.practice.sharedpreferences.sharedpreferencespractice1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private SharedPreferences mPrivatePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrivatePreferences = getSharedPreferences("test", MODE_PRIVATE);
        // privatePreferencesに値が存在すればその値を表示をしてください。
        if (mPrivatePreferences.contains("StringTest")) {
            TextView view = (TextView) findViewById(R.id.tv_string);
            view.setText(mPrivatePreferences.getString("StringTest", ""));
        }
        if (mPrivatePreferences.contains("IntTest")) {
            TextView view = (TextView) findViewById(R.id.tv_int);
            view.setText(String.valueOf(mPrivatePreferences.getInt("IntTest", 0)));
        }
        if (mPrivatePreferences.contains("BooleanTest")) {
            TextView view = (TextView) findViewById(R.id.tv_boolean);
            view.setText(String.valueOf(mPrivatePreferences.getBoolean("BooleanTest", false)));
        }
        if (mPrivatePreferences.contains("LongTest")) {
            TextView view = (TextView) findViewById(R.id.tv_long);
            view.setText(String.valueOf(mPrivatePreferences.getLong("LongTest", 0L)));
        }
        if (mPrivatePreferences.contains("FloatTest")) {
            TextView view = (TextView) findViewById(R.id.tv_float);
            view.setText(String.valueOf(mPrivatePreferences.getFloat("FloatTest", 0.0f)));
        }

        // privatePreferencesにString,int,boolean,long,floatで何らかの値を保存してください。
        SharedPreferences.Editor editor = mPrivatePreferences.edit();
        save(editor);

        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 内容をクリアする処理を書いてください。
                SharedPreferences.Editor editor = mPrivatePreferences.edit();
                editor.clear();
                editor.commit();
            }
        });

    }

    private void save(SharedPreferences.Editor editor) {
        editor.putString("StringTest", "テスト");
        editor.putInt("IntTest", 100);
        editor.putBoolean("BooleanTest", true);
        editor.putLong("LongTest", 98074389L);
        editor.putFloat("FloatTest", 3.14f);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
