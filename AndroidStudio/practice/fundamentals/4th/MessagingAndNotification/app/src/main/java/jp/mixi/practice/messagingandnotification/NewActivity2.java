package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by tomohiro on 16/05/04.
 */
public class NewActivity2 extends Activity {
    public static final String TOAST_MESSAGE = "toast_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_2);

        Intent received = getIntent();
        String message = received.getStringExtra(TOAST_MESSAGE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
