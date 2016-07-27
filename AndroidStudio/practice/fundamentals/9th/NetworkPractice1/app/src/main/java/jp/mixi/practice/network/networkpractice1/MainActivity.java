package jp.mixi.practice.network.networkpractice1;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // ハニーコンボより下、ジンジャーブレッド
            // ジンジャーブレッドからの機能
            // パフォーマンスの劣化につながる要素を特定する
            // 通信に時間がかかっていれば教えてくれる
            // リリース前に取り除くこと
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()// 特定のスレッドに適用するポリシー
                    .detectNetwork()// ネットワーク操作の検出
                    .penaltyDeath()// 違反によってプロセスをクラッシュする
                    .build());
        }
        disableConnectionReuseIfNecessary();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http getの処理を書く
                // mixi.jpにアクセスし取得したhtmlの文字列を表示する
                URL url = null;
                try {
                    url = new URL("http://mixi.jp/");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return;
                }
                HttpURLConnection connection = null;
                StringBuilder src = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream is = connection.getInputStream();

                    src = new StringBuilder();
                    while (true) {
                        byte[] line = new byte[1024];
                        int size = is.read(line);
                        if (size <= 0) {
                            break;
                        }
                        src.append(new String(line, "euc-jp"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
                EditText editText = (EditText) findViewById(R.id.httpBody);
                editText.setText(src.toString());
            }
        });
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く

            }
        });
    }

    private void disableConnectionReuseIfNecessary() {
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
