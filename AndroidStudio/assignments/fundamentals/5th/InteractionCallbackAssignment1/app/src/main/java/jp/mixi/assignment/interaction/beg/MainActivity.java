
package jp.mixi.assignment.interaction.beg;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    private MenuItem menuItemSettings = null;

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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItemSettings = menu.findItem(R.id.action_settings);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // R.id.action_refreshの有効向こうを交互に切り替える
                boolean isEnabled = menuItemSettings.isEnabled();
                menuItemSettings.setEnabled(!isEnabled);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
