package jp.mixi.assignment.async.beg;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by KOBAYASHI Tomohiro on 16/06/29.
 */
public class MyAsyncTaskLoader extends AsyncTaskLoader<PreferencesEntity> {
    @SuppressWarnings("unused")
    private static final String TAG = MyAsyncTaskLoader.class.getSimpleName();
    private Context mContext;
    private PreferencesEntity mPreferencesEntity;

    public MyAsyncTaskLoader(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public PreferencesEntity loadInBackground() {
        SharedPreferences sp = mContext.getSharedPreferences(MainActivity.SAVE_DATA,
                mContext.MODE_PRIVATE);
        PreferencesEntity result = new PreferencesEntity(
                sp.getString("hoge", "no hoge"), sp.getInt("fuga", 0), sp.getBoolean("piyo", false)
        );
        return result;
    }

    @Override
    protected void onReset() {
        onStopLoading();
        super.onReset();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
        super.onStopLoading();
    }

    @Override
    protected void onStartLoading() {
        if (mPreferencesEntity != null) {
            deliverResult(mPreferencesEntity);
            return;
        }
        if (takeContentChanged() || mPreferencesEntity == null) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(PreferencesEntity data) {
        if (isReset()) {
            if (mPreferencesEntity != null) {
                mPreferencesEntity = null;
            }
            return;
        }
        mPreferencesEntity = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }
}
