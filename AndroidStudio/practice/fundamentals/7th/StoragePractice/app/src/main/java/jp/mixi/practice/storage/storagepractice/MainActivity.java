package jp.mixi.practice.storage.storagepractice;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
