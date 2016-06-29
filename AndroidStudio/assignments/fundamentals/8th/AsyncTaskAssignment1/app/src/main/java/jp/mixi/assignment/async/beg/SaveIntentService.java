package jp.mixi.assignment.async.beg;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by KOBAYASHI Tomohiro on 16/06/28.
 */
public class SaveIntentService extends IntentService {
    @SuppressWarnings("unused")
    private static final String TAG = SaveIntentService.class.getSimpleName();

    public SaveIntentService() {
        this(SaveIntentService.class.getSimpleName());
    }
    public SaveIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 保存するデータを受け取る
        PreferencesEntity item = intent.getParcelableExtra("savedata");
        // 保存する
//        Log.d(TAG, item.getHoge());
        SharedPreferences sp = getSharedPreferences(MainActivity.SAVE_DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("hoge", item.getHoge());
        editor.putInt("fuga", item.getFuga());
        editor.putBoolean("piyo", item.isPiyo());
        editor.commit();
    }
}
