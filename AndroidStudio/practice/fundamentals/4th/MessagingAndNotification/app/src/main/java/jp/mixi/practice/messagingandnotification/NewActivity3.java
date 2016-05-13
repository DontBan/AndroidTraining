package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by tomohiro on 16/05/04.
 */
public class NewActivity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_3);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity3.this, NewActivity2.class);
                intent.putExtra(NewActivity2.TOAST_MESSAGE, "NewActivity3に呼び出されました");
                startActivity(intent);
            }
        });
    }
}
