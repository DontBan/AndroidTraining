package jp.mixi.assignment.messagingandnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by suino on 2015/03/04.
 */
public class EditActivity extends ActionBarActivity {
    public static final int REQUEST_CODE = 1;
    public static final String MY_RESULT_MESSAGE = "MyResultMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        View button = findViewById(R.id.Submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここで、id が MyEdit の EditText からテキストを取得して、結果にセットする
                EditText editText = (EditText) findViewById(R.id.MyEdit);
                String resultMessage = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(MY_RESULT_MESSAGE, resultMessage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
