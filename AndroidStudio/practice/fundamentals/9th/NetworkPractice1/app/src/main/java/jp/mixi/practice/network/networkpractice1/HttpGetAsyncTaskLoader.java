package jp.mixi.practice.network.networkpractice1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by KOBAYASHI Tomohiro on 16/07/20.
 */
public class HttpGetAsyncTaskLoader extends AsyncTaskLoader<String> {
    @SuppressWarnings("unused")
    private static final String TAG = HttpGetAsyncTaskLoader.class.getSimpleName();

    public HttpGetAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        return null;
    }
}
