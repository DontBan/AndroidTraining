package jp.mixi.assignment.async.beg;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    public static final String SAVE_DATA = "savedata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: それぞれ、ボタンクリックに反応して、SharedPreferences からの読み込みと保存の処理を実装すること。
        // TODO: 保存、読み込みのためのオブジェクトは、 PreferencesEntity クラスを使用する。
        // TODO: 適宜、保持しているデータを見て書き込みと読み込みを行うこと。
        // TODO: 読み込みが終わったら、Toast で、PreferencesEntity が持っているデータのどれか好きなものを表示する
        View save = findViewById(R.id.SaveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 保存するデータをつくる
                PreferencesEntity item = new PreferencesEntity();
                // SharedPreferencesに保存する
                Intent intent = new Intent(MainActivity.this, SaveIntentService.class);
                intent.putExtra(SAVE_DATA, item);
                startService(intent);
            }
        });
        View load = findViewById(R.id.LoadButton);
    }
}