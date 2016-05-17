
package jp.mixi.assignment.interaction.med;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            TextView counter = (TextView) findViewById(R.id.countEditText);
            counter.setText(String.valueOf(editable.length()));
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.removeTextChangedListener(mTextWatcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
