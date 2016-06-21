package jp.mixi.practice.storage.storagepractice;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String FILE_NAME = "text_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ボタン　内部ストレージに保存を押下した場合
        findViewById(R.id.internalFileSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 入力されたテキストの取得
                EditText text = (EditText) findViewById(R.id.text);
                String s = text.getText().toString();
                // 内部ストレージへ保存する
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(s.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // ボタン　外部ストレージに保存を押下した場合
        findViewById(R.id.externalFileSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 書き込みできるのか確認する
                if (!isExternalStorageWritable()) {
                    return;
                }
                // ファイル名を取得する
                EditText text = (EditText) findViewById(R.id.file);
                String filename = text.getText().toString();
                // 保存する画像をつくる
                Bitmap bmp = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
                Canvas cv = new Canvas(bmp);
                Paint p = new Paint();
                p.setColor(0xffffff00);
                cv.drawRect(0, 0, 32, 64, p);
                p.setColor(0xffff00ff);
                cv.drawRect(32, 0, 64, 64, p);
                // 外部ストレージへ保存する DIRECTORY_PICTURES
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        "temp");
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        Log.e(LOG_TAG, "ディレクトリ作成失敗");
                    }
                }
                String attachName = file.getAbsolutePath() + "/" + filename + ".jpg";
                try {
                    FileOutputStream out = new FileOutputStream(attachName);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    try {
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
