package jp.mixi.assignment.async.beg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
        LoaderCallbacks<PreferencesEntity> {

    public static final String SAVE_DATA = "savedata";
    LoaderManager mManager;

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

        mManager = getSupportLoaderManager();

        View load = findViewById(R.id.LoadButton);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AsyncTaskLoaderで読み出す
                Bundle argsForLoader = new Bundle();
                mManager.initLoader(0, argsForLoader, MainActivity.this);
            }
        });
    }

    @Override
    public Loader<PreferencesEntity> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new MyAsyncTaskLoader(this);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<PreferencesEntity> loader, PreferencesEntity data) {
        Toast.makeText(this, data.getHoge(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<PreferencesEntity> loader) {

    }
}